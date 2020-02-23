package com.m2comm.kingca2020.viewmodels;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;

import com.m2comm.kingca2020.R;
import com.m2comm.kingca2020.databinding.FragmentBottomBinding;
import com.m2comm.kingca2020.modules.common.Custom_SharedPreferences;
import com.m2comm.kingca2020.modules.common.Globar;
import com.m2comm.kingca2020.views.ContentsActivity;
import com.m2comm.kingca2020.views.GlanceActivity;
import com.m2comm.kingca2020.views.MainActivity;


public class BottomViewModel implements View.OnClickListener {

    private Context c;
    private Globar g;
    private FragmentBottomBinding binding;
    private Custom_SharedPreferences csp;

    public BottomViewModel(FragmentBottomBinding binding, Context c ) {
        this.binding = binding;
        this.c = c;
        this.init();
    }

    private void listenerRegister() {
        this.binding.home.setOnClickListener(this);
        this.binding.search.setOnClickListener(this);
        this.binding.now.setOnClickListener(this);
        this.binding.fav.setOnClickListener(this);
        this.binding.program.setOnClickListener(this);
    }

    private void init() {
        this.g = new Globar(this.c);
        this.csp = new Custom_SharedPreferences(this.c);
        this.listenerRegister();
    }


    @Override
    public void onClick(View v) {
        Activity a = (Activity)this.c;
        Intent intent;
        switch (v.getId()) {

            case R.id.home:
                if ( this.csp.getValue("code","").equals(g.code) ) intent = new Intent(c, MainActivity.class);
                else intent = new Intent(c, MainActivity.class);

                intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                a.startActivity(intent);
                break;

            case R.id.program:
//                if ( this.csp.getValue("code","").equals(g.code) ) intent = new Intent(c, GlanceActivity.class);
//                else intent = new Intent(c, New_GlanceActivity.class);
//
//                a.startActivity(intent);
//                a.overridePendingTransition(R.anim.anim_slide_in_right, R.anim.anim_slide_out_left);
                break;

            case R.id.now:
//                if ( this.csp.getValue("code","").equals(g.code) ) intent = new Intent(this.c, ContentsActivity.class);
//                else intent = new Intent(this.c, com.m2comm.ksic2019summer.views.spring2020.ContentsActivity.class);
//
//                intent.putExtra("paramUrl", this.g.urls.get("now"));
//                a.startActivity(intent);
//                a.overridePendingTransition(R.anim.anim_slide_in_right, R.anim.anim_slide_out_left);
                break;

            case R.id.search:
//                if ( this.csp.getValue("code","").equals(g.code) ) intent = new Intent(this.c, ContentsActivity.class);
//                else intent = new Intent(this.c, com.m2comm.ksic2019summer.views.spring2020.ContentsActivity.class);
//
//                intent.putExtra("paramUrl", this.g.urls.get("search"));
//                a.startActivity(intent);
//                a.overridePendingTransition(R.anim.anim_slide_in_right, R.anim.anim_slide_out_left);
                break;

            case R.id.fav:
//                if ( this.csp.getValue("code","").equals(g.code) ) intent = new Intent(this.c, ContentsActivity.class);
//                else intent = new Intent(this.c, com.m2comm.ksic2019summer.views.spring2020.ContentsActivity.class);
//
//                intent.putExtra("paramUrl", this.g.urls.get("mySchedule"));
//                a.startActivity(intent);
//                a.overridePendingTransition(R.anim.anim_slide_in_right, R.anim.anim_slide_out_left);
                break;
        }
    }
}
