package com.m2comm.kingca2020.viewmodels;


import android.content.Context;

import com.m2comm.kingca2020.modules.common.Custom_SharedPreferences;
import com.m2comm.kingca2020.modules.common.Globar;

public class ContentViewModel {

    private Context c;
    private Custom_SharedPreferences csp;

    private Globar g;

    public String paramUrl;
    public String title;

    private void listenerRegister() {

    }

    public ContentViewModel( Context c , String paramUrl , String title , Boolean content) {
        this.c = c;
        this.paramUrl = paramUrl;
        this.title = title;
        this.init();
        this.listenerRegister();
    }

    private void init() {
        this.csp = new Custom_SharedPreferences(this.c);
        this.g = new Globar(this.c);
        this.g.addFragment_Content_TopV(this.c);
    }









}
