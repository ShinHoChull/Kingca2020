package com.m2comm.kingca2020.views;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.m2comm.kingca2020.R;
import com.m2comm.kingca2020.databinding.ActivityFindBinding;
import com.m2comm.kingca2020.viewmodels.FindViewModel;

public class FindActivity extends AppCompatActivity {

    private ActivityFindBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.binding = DataBindingUtil.setContentView(this, R.layout.activity_find);
        this.binding.setFind(this);
        new FindViewModel(this.binding , this );
    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        finish();
        overridePendingTransition(0, R.anim.anim_slide_out_left);
    }
}
