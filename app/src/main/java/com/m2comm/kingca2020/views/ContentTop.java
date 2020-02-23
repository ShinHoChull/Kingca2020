package com.m2comm.kingca2020.views;

import android.databinding.DataBindingUtil;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.m2comm.kingca2020.R;
import com.m2comm.kingca2020.databinding.FragmentContentTopBinding;
import com.m2comm.kingca2020.viewmodels.CTopViewModel;

public class ContentTop extends Fragment {

    FragmentContentTopBinding binding;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        this.binding = DataBindingUtil.inflate(inflater, R.layout.fragment_content_top,container,false);
        new CTopViewModel(this.binding,getContext());
        return this.binding.getRoot();

    }
}
