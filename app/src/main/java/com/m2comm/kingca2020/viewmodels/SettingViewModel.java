package com.m2comm.kingca2020.viewmodels;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Message;
import android.util.Log;
import android.view.View;

import com.google.gson.Gson;
import com.google.gson.JsonElement;

import com.m2comm.kingca2020.R;
import com.m2comm.kingca2020.databinding.ActivitySettingBinding;
import com.m2comm.kingca2020.models.MessageDTO;
import com.m2comm.kingca2020.modules.common.CustomHandler;
import com.m2comm.kingca2020.modules.common.Custom_SharedPreferences;
import com.m2comm.kingca2020.modules.common.Globar;
import com.m2comm.kingca2020.views.MainActivity;

public class SettingViewModel implements View.OnClickListener {


    ActivitySettingBinding activity;
    private Globar g;
    private Context c;
    private Custom_SharedPreferences csp;
    private CustomHandler customHandler;
    private String push;


    public SettingViewModel(ActivitySettingBinding activity , Context c) {
        this.activity = activity;
        this.c = c;
        this.init();
    }

    private void init () {
        this.g = new Globar(this.c);
        this.csp = new Custom_SharedPreferences(this.c);
        this.customHandler = new CustomHandler(this.c);

        this.g.addFragment_Content_TopV(this.c);
        this.g.addFragment_Sub_TopV(this.c , "Setting");
        this.listenerRegister();

        this.getPush();

    }

    private void listenerRegister() {
        this.activity.settingLogout.setOnClickListener(this);
        this.activity.settingOp1.setOnClickListener(this);
    }

    public void getPush () {
        new Thread() {
            @Override
            public void run() {
                super.run();
                Gson gson = new Gson();
                Message msg = customHandler.obtainMessage();
                try {
                    Log.d("settingJE",g.baseUrl+g.urls.get("getPush")+"?deviceid="+csp.getValue("deviceid","")+
                            "&code="+g.code);
                    JsonElement je = g.getParser(g.baseUrl+g.urls.get("getPush")+"?deviceid="+csp.getValue("deviceid","")+
                            "&code="+g.code);

                    Log.d("settingJE",je.getAsString());
                    push = je.getAsString();
                    msg.obj = je.getAsString();
                    msg.what = CustomHandler.SETTING_GET_CODE;
                    customHandler.sendMessage(msg);

                } catch (Exception e) {

                    msg.obj = new MessageDTO("Failed to fetch data.(Setting Error)",
                            e.toString());
                    msg.what = CustomHandler.ALERT_WINDOW_CODE;
                    customHandler.sendMessage(msg);
                }
            }
        }.start();
    }

    public void setPush() {
        if (this.push.equals("Y")) {
            this.push = "N";
        } else {
            this.push = "Y";
        }

        new Thread() {
            @Override
            public void run() {
                super.run();
                Gson gson = new Gson();
                Message msg = customHandler.obtainMessage();
                try {
                    JsonElement je = g.getParser(g.baseUrl+g.urls.get("setPush")+"?deviceid="+csp.getValue("deviceid","")+
                            "&code="+g.code+"&val="+push);

                    push = je.getAsString();
                    msg.obj = je.getAsString();
                    msg.what = CustomHandler.SETTING_GET_CODE;
                    customHandler.sendMessage(msg);

                } catch (Exception e) {

                    msg.obj = new MessageDTO("Failed to fetch data.(Setting Error)",
                            e.toString());
                    msg.what = CustomHandler.ALERT_WINDOW_CODE;
                    customHandler.sendMessage(msg);
                }
            }
        }.start();

    }


    public void settingChange (String val) {
        Log.d("settingJEMeth",val);
        if (val.equals("Y")) {
            this.activity.settingOp1.setImageResource(R.drawable.togleon);
        } else {
            this.activity.settingOp1.setImageResource(R.drawable.togleoff);
        }
    }


    @Override
    public void onClick(View v) {
        Activity a = (Activity)this.c;
        switch (v.getId()) {
            case R.id.setting_logout :
                this.csp.remove("userid");
                this.csp.remove("reg_num");
                this.csp.remove("isLogin");
                this.g.msg("You have been signed out.");

                Intent content = new Intent(c, MainActivity.class);
                content.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                content.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                a.startActivity(content);
                a.finish();
                a.overridePendingTransition(R.anim.anim_slide_in_left,R.anim.anim_slide_out_right);
                break;
            case R.id.setting_op1:
                setPush();
                break;
        }

    }
}
