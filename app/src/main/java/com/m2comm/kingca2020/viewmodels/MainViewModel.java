package com.m2comm.kingca2020.viewmodels;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Point;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.AdapterView;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import com.m2comm.kingca2020.R;
import com.m2comm.kingca2020.databinding.ActivityMainBinding;
import com.m2comm.kingca2020.models.BannerDTO;
import com.m2comm.kingca2020.models.MessageDTO;
import com.m2comm.kingca2020.models.NoticeDTO;
import com.m2comm.kingca2020.modules.adapters.CustomGridViewAdapter;
import com.m2comm.kingca2020.modules.adapters.CustomPagerAdapter;
import com.m2comm.kingca2020.modules.common.CustomHandler;
import com.m2comm.kingca2020.modules.common.Custom_SharedPreferences;
import com.m2comm.kingca2020.modules.common.Globar;
import com.m2comm.kingca2020.views.Bottom;
import com.m2comm.kingca2020.views.ContentsActivity;
import com.m2comm.kingca2020.views.GlanceActivity;
import com.m2comm.kingca2020.views.PhotoActivity;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class MainViewModel implements View.OnClickListener, AdapterView.OnItemClickListener {

    //main Banner
    private TimerTask mTask;
    private Timer mtimer;
    private int timer = 5000;

    private ActivityMainBinding activity;
    private Context c;
    private Globar g;
    //Banner List
    private ArrayList<BannerDTO> bannerArray;
    //Notice List
    private ArrayList<NoticeDTO> noticeArray;
    //handler
    private CustomHandler customHandler;
    private Custom_SharedPreferences csp;
    private int newNotiSid;


    private void listenerRegister() {
        this.activity.mainGrid.setOnItemClickListener(this);
        this.activity.mainNoticeV.setOnClickListener(this);
    }

    public MainViewModel(ActivityMainBinding activity, Context c) {
        this.activity = activity;
        this.c = c;
        this.init();
    }

    private void getDisplayInfo() {
        Activity a = (Activity)this.c;
        Display display = a.getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
    }

    public void onResume() {
        LayoutInflater inflater = (LayoutInflater) this.c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final CustomGridViewAdapter cga = new CustomGridViewAdapter(this.c, inflater);
        this.activity.mainGrid.post(new Runnable() {
            @Override
            public void run() {
                activity.mainGrid.setAdapter(cga);
            }
        });
    }

    private void init() {
        this.g = new Globar(this.c);
        this.csp = new Custom_SharedPreferences(this.c);
        this.customHandler = new CustomHandler(this.c);
        this.noticeArray = new ArrayList<>();
        this.g.addFragmentTopV(this.c);
        this.g.addFragment_BottomV(this.c);
        this.listenerRegister();
        this.getDisplayInfo();

        //메인 배너 이미지 다운로드 받기
        new Thread() {
            @Override
            public void run() {
                super.run();
                Gson gson = new Gson();
                Message msg = customHandler.obtainMessage();
                try {
                    JsonElement je = g.getParser(g.baseUrl + g.urls.get("banner"));
                    Log.d("banner", "" + je);
                    Type listType = new TypeToken<ArrayList<BannerDTO>>() {
                    }.getType();
                    ArrayList<BannerDTO> bannerArr = gson.fromJson(je, listType);
                    bannerArray = bannerArr;

                    msg.what = CustomHandler.MAIN_PAGER_CODE;
                    customHandler.sendMessage(msg);
                } catch (Exception e) {
                    msg.obj = new MessageDTO("Failed to fetch data.(Banner Error)",
                            e.toString());
                    msg.what = CustomHandler.ALERT_WINDOW_CODE;
                    customHandler.sendMessage(msg);
                }
            }
        }.start();

        //메인 공지 다운로드 받기
        new Thread() {
            @Override
            public void run() {
                super.run();
                Gson gson = new Gson();
                Message msg = customHandler.obtainMessage();
                try {
                    JsonElement je = g.getParser(g.baseUrl + g.urls.get("getNoti"));
                    JsonArray ja = je.getAsJsonArray();
                    for (int i = 0, j = ja.size(); i < j; i++) {
                        JsonObject jsonObject = ja.get(i).getAsJsonObject();
                        Log.d("jajaString", jsonObject.get("subject").getAsString());

                        noticeArray.add(new NoticeDTO(jsonObject.get("sid").getAsInt(), jsonObject.get("subject").getAsString(),
                                jsonObject.get("new").getAsString(), jsonObject.get("signdate").getAsString()));
                    }
                    msg.what = CustomHandler.MAIN_NOTICE;
                    customHandler.sendMessage(msg);
                } catch (Exception e) {
                    msg.obj = new MessageDTO("Failed to fetch data.(Banner Error)",
                            e.toString());
                    msg.what = CustomHandler.ALERT_WINDOW_CODE;
                    customHandler.sendMessage(msg);
                }
            }
        }.start();

        FirebaseInstanceId.getInstance().getToken();
        if (FirebaseInstanceId.getInstance().getToken() != null) {
            Log.d("pushToken",FirebaseInstanceId.getInstance().getToken());
            new Thread() {
                @Override
                public void run() {
                    super.run();
                    Message msg = customHandler.obtainMessage();
                    try {
                        g.getParser(g.baseUrl + g.urls.get("setToken") + "?deviceid=" + csp.getValue("deviceid", "") +
                                "&device=android&code=" + g.code + "&token=" + FirebaseInstanceId.getInstance().getToken());

                    } catch (Exception e) {
                        msg.obj = new MessageDTO("Failed to fetch data.(Token Error)",
                                e.toString());
                        msg.what = CustomHandler.ALERT_WINDOW_CODE;
                        customHandler.sendMessage(msg);
                    }
                }
            }.start();
        }
    }

    public void notiChange() {
        if (this.noticeArray.size() > 0) {
            NoticeDTO r = this.noticeArray.get(0);
            this.newNotiSid = r.getSid();
            this.activity.mainNotiTextV.setText(r.getSubject());
        }
    }

    public void bannerTimer() {

        LayoutInflater inflater = (LayoutInflater) this.c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        CustomPagerAdapter adapter = new CustomPagerAdapter(this.c.getApplicationContext(), inflater, this.bannerArray);
        this.activity.viewpager.setAdapter(adapter);

        if (this.bannerArray.size() > 1) {
            mTask = new TimerTask() {
                @Override
                public void run() {
                    Message msg = customHandler.obtainMessage();
                    msg.what = CustomHandler.MAIN_SLIDE_CODE;
                    customHandler.sendMessage(msg);
                }
            };
            mtimer = new Timer();
            mtimer.schedule(mTask, this.timer, this.timer);
        }
    }

    public void slideMove() {
        int postion = this.activity.viewpager.getCurrentItem();
        if (postion >= this.bannerArray.size() - 1) {
            this.activity.viewpager.setCurrentItem(0, false);
        } else {
            this.activity.viewpager.setCurrentItem(postion + 1, true);
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        String tempTitle = "";
        Activity a = (Activity) c;
        Intent content = new Intent(c, ContentsActivity.class);
        switch (position) {
            case 0:
                //Kingca
                tempTitle = "Kingca";
                content.putExtra("content", true);
                break;

            case 1:
                //program
                Intent intent = new Intent(this.c, GlanceActivity.class);
                a.startActivity(intent);
                a.overridePendingTransition(R.anim.anim_slide_in_right, R.anim.anim_slide_out_left);
                return;

            case 2:
                if (!csp.getValue("isLogin", false)) {
                    this.g.loginMainAlertMessage("Alert", "Do you want to sign in?", a);
                    return;
                }
                //abstract
                tempTitle = "Abstract";
                break;
            case 3:
                //speakers
                tempTitle = "Speakers";
                break;
            case 4:
                //venue floor plan
                content.putExtra("content", true);
                break;
            case 5:
                //general information
                content.putExtra("content", true);
                break;
            case 6:
                //photo gallery
                Intent photo = new Intent(c, PhotoActivity.class);
                c.startActivity(photo);
                a.overridePendingTransition(R.anim.anim_slide_in_right, R.anim.anim_slide_out_left);
                return;
            case 7:
                //sponsors & booth
                content.putExtra("content", true);
                break;
            case 8:
                //notice
                tempTitle = "Notice";
                break;
        }

        content.putExtra("paramUrl", this.g.mainUrls[position]);
        content.putExtra("title", tempTitle);
        c.startActivity(content);
        a.overridePendingTransition(R.anim.anim_slide_in_right, R.anim.anim_slide_out_left);
    }

    @Override
    public void onClick(View v) {
        Activity a = (Activity) c;
        switch (v.getId()) {
            case R.id.main_noticeV:
                Log.d("mainView", this.g.baseUrl + this.g.urls.get("detailNoti") + "&sid=" + this.newNotiSid);
                Intent content = new Intent(c, ContentsActivity.class);
                content.putExtra("paramUrl", this.g.baseUrl + this.g.urls.get("detailNoti") + "&sid=" + this.newNotiSid);
                c.startActivity(content);
                a.overridePendingTransition(R.anim.anim_slide_in_right, R.anim.anim_slide_out_left);
                break;
        }
    }

}
