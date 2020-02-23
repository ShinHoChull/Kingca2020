package com.m2comm.kingca2020.viewmodels;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.m2comm.kingca2020.R;
import com.m2comm.kingca2020.databinding.FragmentTopBinding;
import com.m2comm.kingca2020.modules.common.Custom_SharedPreferences;
import com.m2comm.kingca2020.modules.common.Globar;
import com.m2comm.kingca2020.views.ContentsActivity;
import com.m2comm.kingca2020.views.MenuActivity;

public class TopViewModel implements View.OnClickListener {

    private Context c;
    private Globar g;
    private FragmentTopBinding activity;
    private Custom_SharedPreferences csp;

    public TopViewModel(FragmentTopBinding activity, Context c) {
        this.activity = activity;
        this.c = c;
        this.init();
    }

    private void listenerRegister() {
        this.activity.topMenu.setOnClickListener(this);
        this.activity.topLogo.setOnClickListener(this);
        this.activity.topSearch.setOnClickListener(this);
        this.activity.mySchedule.setOnClickListener(this);
    }

    public void init() {
        this.g = new Globar(this.c);
        this.csp = new Custom_SharedPreferences(this.c);
        this.listenerRegister();
    }

    @Override
    public void onClick(View v) {
        Activity a = (Activity)this.c;
        switch (v.getId()) {

            case R.id.top_menu:
                Intent menuCall= new Intent(this.c, MenuActivity.class);
                menuCall.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                a.startActivity(menuCall);
                a.overridePendingTransition(R.anim.anim_slide_in_left,0);
                break;

            case R.id.top_logo:

                break;

            case R.id.mySchedule:

                if (!csp.getValue("isLogin",false)) {
                    this.g.loginAlertMessage("Alert", "Do you want to sign in?", a);
                    return;
                }

                Intent content = new Intent(this.c, ContentsActivity.class);
                content.putExtra("paramUrl", this.g.urls.get("myschedule"));
                content.putExtra("title", "My Schedule");
                a.startActivity(content);
                a.overridePendingTransition(R.anim.anim_slide_in_right, R.anim.anim_slide_out_left);
                break;

            case R.id.top_search:
                Intent search = new Intent(this.c, ContentsActivity.class);
                search.putExtra("paramUrl", this.g.urls.get("search"));
                search.putExtra("title", "Search");
                a.startActivity(search);
                a.overridePendingTransition(R.anim.anim_slide_in_right, R.anim.anim_slide_out_left);
                break;
        }

    }
}
