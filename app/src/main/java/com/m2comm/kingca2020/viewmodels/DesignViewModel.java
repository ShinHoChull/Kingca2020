package com.m2comm.kingca2020.viewmodels;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.widget.TextView;

import com.m2comm.kingca2020.R;
import com.m2comm.kingca2020.databinding.ActivityDesignBinding;
import com.m2comm.kingca2020.modules.common.Custom_SharedPreferences;

import java.util.ArrayList;
import java.util.List;

public class DesignViewModel implements View.OnClickListener {

    Activity activity;
    Context context;
    ActivityDesignBinding binding;
    private Custom_SharedPreferences csp;
    private int defaultSelectType = 1;


    private void regObj () {
        this.binding.designTypeSelectBt1.setOnClickListener(this);
        this.binding.designTypeSelectBt2.setOnClickListener(this);
        this.binding.designTypeSelectBt3.setOnClickListener(this);
    }

    public DesignViewModel(Activity activity, Context context, ActivityDesignBinding binding) {
        this.activity = activity;
        this.context = context;
        this.binding = binding;
        this.init();
    }

    private void init () {
        this.regObj();
        this.csp = new Custom_SharedPreferences(this.context);
    }

    private void resetBt() {
        this.binding.designTypeSelectBt1.setTextColor(Color.parseColor("#075e94"));
        this.binding.designTypeSelectBt2.setTextColor(Color.parseColor("#075e94"));
        this.binding.designTypeSelectBt3.setTextColor(Color.parseColor("#075e94"));
        this.binding.designTypeSelectBt1.setBackgroundColor(Color.parseColor("#00000000"));
        this.binding.designTypeSelectBt2.setBackgroundColor(Color.parseColor("#00000000"));
        this.binding.designTypeSelectBt3.setBackgroundColor(Color.parseColor("#00000000"));
        this.binding.designTypeBox.setBackground(this.context.getResources().getDrawable(R.drawable.degin_type_select_radius));
    }


    private void changeType (TextView tv,  int type) {
        this.resetBt();
        this.csp.put("designType",type);
        tv.setTextColor(Color.parseColor("#FFFFFF"));
        if ( type == 1 ) {
            tv.setBackground(this.context.getResources().getDrawable(R.drawable.degin_type_select1_radius));
            this.binding.designTypeImg.setImageResource(R.drawable.design_type1);
        } else if ( type == 3 ) {
            tv.setBackground(this.context.getResources().getDrawable(R.drawable.degin_type_select3_radius));
            this.binding.designTypeImg.setImageResource(R.drawable.design_type3);
        } else {
            tv.setBackgroundColor(Color.parseColor("#075e94"));
            this.binding.designTypeImg.setImageResource(R.drawable.design_type2);
        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.design_type_selectBt1:
                this.changeType(this.binding.designTypeSelectBt1,1);
                break;

            case R.id.design_type_selectBt2:
                this.changeType(this.binding.designTypeSelectBt2,2);
                break;

            case R.id.design_type_selectBt3:
                this.changeType(this.binding.designTypeSelectBt3,3);
                break;
        }
    }
}
