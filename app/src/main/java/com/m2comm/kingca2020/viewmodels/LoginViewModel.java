package com.m2comm.kingca2020.viewmodels;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.StringRequestListener;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import com.m2comm.kingca2020.R;
import com.m2comm.kingca2020.databinding.ActivityLoginBinding;
import com.m2comm.kingca2020.models.LoginDTO;
import com.m2comm.kingca2020.modules.common.CustomHandler;
import com.m2comm.kingca2020.modules.common.Custom_SharedPreferences;
import com.m2comm.kingca2020.modules.common.Globar;
import com.m2comm.kingca2020.views.ContentsActivity;
import com.m2comm.kingca2020.views.MainActivity;

import java.lang.reflect.Type;

public class LoginViewModel implements View.OnClickListener {

    private ActivityLoginBinding activity;
    private Context c;
    private Globar g;
    private Custom_SharedPreferences csp;
    private CustomHandler customHandler;
    private Activity aa;


    public LoginViewModel(ActivityLoginBinding activity, Context c , Activity a) {
        this.activity = activity;
        this.c = c;
        this.aa = a;
        this.init();
    }

    private void listenerRegister() {
        this.activity.loginCloseBt.setOnClickListener(this);
        this.activity.loginLoginBt.setOnClickListener(this);
        this.activity.loginFindEmail.setOnClickListener(this);
    }

    public void init() {
        this.g = new Globar(this.c);
        this.csp = new Custom_SharedPreferences(this.c);
        this.customHandler = new CustomHandler(this.c);

        this.listenerRegister();
    }

    public void login () {
        Activity a = (Activity)this.c;
        if ( this.activity.loginEmail.getText().toString().equals("") ) {
            this.g.baseAlertMessage("Alert","Please enter your email");
        } else if ( this.activity.loginPassword.getText().toString().equals("") ) {
            this.g.baseAlertMessage("Alert","Please enter your password");
        } else {

            AndroidNetworking.get(g.contentUrl+g.urls.get("login"))
                    .addQueryParameter("deviceid", csp.getValue("deviceid",""))
                    .addQueryParameter("code", g.code)
                    .addQueryParameter("id", activity.loginEmail.getText().toString())
                    .addQueryParameter("passwd",activity.loginPassword.getText().toString())
                    .setPriority(Priority.LOW)
                    .build().getAsString(new StringRequestListener() {
                @Override
                public void onResponse(String response) {
                    loginSuccess(response);
                }

                @Override
                public void onError(ANError anError) {
                    g.baseAlertMessage("Alert", anError.toString());
                }
            });
        }
    }

    public void loginSuccess(String val) {
        Activity a = (Activity)c;

        if (val.contains("Please")) {
            this.g.baseAlertMessage("Alert",val);
        } else {

            Type listType = new TypeToken<LoginDTO>() {
            }.getType();
            Gson gson = new Gson();
            LoginDTO r = gson.fromJson(val, listType);
            csp.put("userid",r.getId());
            csp.put("reg_num",r.getReg_num());
            csp.put("isLogin",true);

            Intent content = new Intent(c, MainActivity.class);
            content.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
            content.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            a.startActivity(content);
            a.finish();
        }
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.login_closeBt:
                Activity a = (Activity)this.c;
                a.finish();
                a.overridePendingTransition(0,R.anim.anim_slide_out_bottom_login);
                break;

            case R.id.login_loginBt:
                this.login();
                break;
            case R.id.login_findEmail:

                Intent content = new Intent(this.c, ContentsActivity.class);
                content.putExtra("content", true);
                content.putExtra("paramUrl", this.g.urls.get("findAccount"));
                c.startActivity(content);
                aa.overridePendingTransition(R.anim.anim_slide_in_right, R.anim.anim_slide_out_left);
//                Intent content = new Intent(c, FindActivity.class);
//                c.startActivity(content);
//                aa.overridePendingTransition(R.anim.anim_slide_in_right, R.anim.anim_slide_out_left);
                break;
        }

    }
}
