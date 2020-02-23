package com.m2comm.kingca2020.viewmodels;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.m2comm.kingca2020.R;
import com.m2comm.kingca2020.databinding.FragmentGlanceTopBinding;
import com.m2comm.kingca2020.models.GlanceChild;
import com.m2comm.kingca2020.models.GlanceDTO;
import com.m2comm.kingca2020.modules.common.Globar;
import com.m2comm.kingca2020.views.MainActivity;
import com.m2comm.kingca2020.views.MenuActivity;

public class GTopViewModel implements View.OnClickListener {
    private Context c;
    private Globar g;
    private FragmentGlanceTopBinding binding;
    private GlanceDTO glanceDTO;

    public GTopViewModel(FragmentGlanceTopBinding binding, Context c , GlanceDTO glanceDTO) {
        this.binding = binding;
        this.c = c;
        this.glanceDTO = glanceDTO;
        this.init();
    }

    private void listenerRegister() {

    }

    public void init() {
        this.g = new Globar(this.c);
        this.listenerRegister();
        Log.d("GtopMenuBG",glanceDTO.getGlanceList().size()+"");
        LayoutInflater inflater = (LayoutInflater)this.c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        for (int i = 0 , j = glanceDTO.getGlanceList().size(); i < j; i++) {
            GlanceChild r = glanceDTO.getGlanceList().get(i);
            View v = inflater.inflate(R.layout.glance_daynum_item,this.binding.gDaynumTop,false);

            LinearLayout.LayoutParams  param = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            param.weight = 1;
            param.height = this.g.h(30);
            //param.width = 50;
            v.setLayoutParams(param);
            TextView tv = v.findViewById(R.id.tab1_text);
            tv.setText(r.getDay());
            tv.setTextColor(Color.BLACK);
            this.binding.gDaynumTop.addView(v);

            View weekV = inflater.inflate(R.layout.glance_week_item,this.binding.glanceWeek,false);
            LinearLayout.LayoutParams param1 = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            param1.weight = 1;
            param1.height = this.g.h(10);
            v.setLayoutParams(param1);
            TextView tv1 = weekV.findViewById(R.id.week);
            tv1.setText(r.getWeek());
            tv1.setTextColor(this.c.getResources().getColor(R.color.main_color_black));
            this.binding.glanceWeek.addView(v);


        }
    }

    @Override
    public void onClick(View v) {
        Activity a = (Activity) this.c;
        switch (v.getId()) {

            case R.id.ctop_menu:
                Intent menuCall = new Intent(this.c, MenuActivity.class);
                menuCall.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                a.startActivity(menuCall);
                a.overridePendingTransition(R.anim.anim_slide_in_left, 0);
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
