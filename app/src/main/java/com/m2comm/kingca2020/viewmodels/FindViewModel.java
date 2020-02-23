package com.m2comm.kingca2020.viewmodels;

import android.app.Activity;
import android.content.Context;
import android.view.View;


import com.m2comm.kingca2020.R;
import com.m2comm.kingca2020.databinding.ActivityFindBinding;
import com.m2comm.kingca2020.modules.common.Custom_SharedPreferences;
import com.m2comm.kingca2020.modules.common.Globar;

public class FindViewModel  implements View.OnClickListener {

    private ActivityFindBinding activity;
    private Context c;
    private Globar g;
    private Custom_SharedPreferences csp;


    public FindViewModel(ActivityFindBinding activity, Context c) {
        this.activity = activity;
        this.c = c;
        this.init();
    }

    private void listenerRegister() {
        this.activity.findBt.setOnClickListener(this);
    }

    public void init() {
        this.g = new Globar(this.c);
        this.csp = new Custom_SharedPreferences(this.c);

        this.g.addFragment_Content_TopV(this.c);
        this.g.addFragment_Sub_TopV(this.c , "Find account");
        this.listenerRegister();
    }

    public void login () {
        Activity a = (Activity)this.c;

        if ( this.activity.findId.getText().toString().equals("") ) {
            this.g.baseAlertMessage("Alert","Please enter your email");
        } else {
          //  csp.put("isLogin",true);
            //a.finish();
        }
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.findBt:
                this.login();
                break;

        }

    }

}
