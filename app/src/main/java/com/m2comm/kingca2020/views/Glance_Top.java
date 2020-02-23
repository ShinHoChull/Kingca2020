package com.m2comm.kingca2020.views;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.m2comm.kingca2020.R;
import com.m2comm.kingca2020.databinding.FragmentGlanceTopBinding;
import com.m2comm.kingca2020.models.GlanceDTO;
import com.m2comm.kingca2020.viewmodels.GTopViewModel;

public class Glance_Top extends Fragment {

    FragmentGlanceTopBinding binding;

    public Glance_Top() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        this.binding = DataBindingUtil.inflate(inflater, R.layout.fragment_glance_top,container,false);
        new GTopViewModel(this.binding,getContext(),(GlanceDTO) getArguments().getSerializable("dto"));
        return this.binding.getRoot();
    }
}
