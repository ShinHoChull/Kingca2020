package com.m2comm.kingca2020.views;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.m2comm.kingca2020.R;
import com.m2comm.kingca2020.databinding.FragmentSubTopBinding;
import com.m2comm.kingca2020.viewmodels.SubTopViewModel;

public class SubTop extends Fragment {

    FragmentSubTopBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        this.binding = DataBindingUtil.inflate(inflater, R.layout.fragment_sub_top,container,false);
        new SubTopViewModel(this.binding,getContext(),getArguments().getString("title",""));
        return this.binding.getRoot();

    }


}
