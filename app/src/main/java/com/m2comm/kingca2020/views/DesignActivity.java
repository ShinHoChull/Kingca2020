package com.m2comm.kingca2020.views;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.m2comm.kingca2020.R;
import com.m2comm.kingca2020.databinding.ActivityDesignBinding;
import com.m2comm.kingca2020.viewmodels.DesignViewModel;
import com.m2comm.kingca2020.viewmodels.MainViewModel;

public class DesignActivity extends AppCompatActivity {

    ActivityDesignBinding binding;
    DesignViewModel dvm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.binding = DataBindingUtil.setContentView(this, R.layout.activity_design);
        this.binding.setMain(this);
        this.dvm = new DesignViewModel(this , this , this.binding );
    }
}
