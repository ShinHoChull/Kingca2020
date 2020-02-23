package com.m2comm.kingca2020.views;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.m2comm.kingca2020.R;
import com.m2comm.kingca2020.databinding.FragmentTopBinding;
import com.m2comm.kingca2020.viewmodels.TopViewModel;

public class Top extends Fragment {

    FragmentTopBinding binding;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        this.binding = DataBindingUtil.inflate(inflater, R.layout.fragment_top,container,false);
        new TopViewModel(this.binding,getContext());
        return this.binding.getRoot();

    }





}
