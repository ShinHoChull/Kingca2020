package com.m2comm.kingca2020.viewmodels;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.View;


import com.m2comm.kingca2020.R;
import com.m2comm.kingca2020.databinding.FragmentContentTopBinding;
import com.m2comm.kingca2020.modules.common.Globar;
import com.m2comm.kingca2020.views.MainActivity;
import com.m2comm.kingca2020.views.MenuActivity;

public class CTopViewModel implements View.OnClickListener {

    private Context c;
    private Globar g;
    private FragmentContentTopBinding activity;

    public CTopViewModel(FragmentContentTopBinding activity, Context c) {
        this.activity = activity;
        this.c = c;
        this.init();
    }

    private void listenerRegister() {
        this.activity.ctopMenu.setOnClickListener(this);
        this.activity.ctopLogo.setOnClickListener(this);
    }

    public void init() {
        this.g = new Globar(this.c);
        this.listenerRegister();
    }

    @Override
    public void onClick(View v) {
        Activity a = (Activity)this.c;
        switch (v.getId()) {

            case R.id.ctop_menu:
                Intent menuCall= new Intent(this.c, MenuActivity.class);
                menuCall.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                a.startActivity(menuCall);
                a.overridePendingTransition(R.anim.anim_slide_in_left,0);
                break;

            case R.id.ctop_logo:
                Intent content = new Intent(c, MainActivity.class);
                content.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                a.startActivity(content);
                a.overridePendingTransition(R.anim.anim_slide_in_left, R.anim.anim_slide_out_right);
                break;

        }

    }
}
