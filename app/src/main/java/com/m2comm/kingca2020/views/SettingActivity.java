package com.m2comm.kingca2020.views;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;


import com.m2comm.kingca2020.R;
import com.m2comm.kingca2020.databinding.ActivitySettingBinding;
import com.m2comm.kingca2020.modules.common.Globar;
import com.m2comm.kingca2020.viewmodels.SettingViewModel;

public class SettingActivity extends AppCompatActivity {

    ActivitySettingBinding binding;
    SettingViewModel svm;
    private Globar g;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.binding = DataBindingUtil.setContentView(this, R.layout.activity_setting);
        this.binding.setMain(this);
        this.svm = new SettingViewModel(this.binding , this);
    }

    public void settingChange(String v) {
        this.svm.settingChange(v);
    }





}
