package com.m2comm.kingca2020.views;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.m2comm.kingca2020.R;
import com.m2comm.kingca2020.modules.common.Custom_SharedPreferences;
import com.m2comm.kingca2020.modules.common.Globar;


public class InfoActivity extends Activity {

    private ViewPager viewPager;
    //ArrayList<InfoDTO> arrayList;
    int[] info_imgs = {
            R.drawable.info_img1,
            R.drawable.info_img2,
            R.drawable.info_img3
    };
    int[] info_txt = {
            R.drawable.info_txt1,
            R.drawable.info_txt2,
            R.drawable.info_txt3,
    };



    private LinearLayout info_backgroundV;

    //handler
    //private CustomHandler customHandler;
    //share
    private Custom_SharedPreferences csp;
    private Globar g;

    private TextView closeBt;

    public void viewReset() {

        this.csp = new Custom_SharedPreferences(this);
        this.g = new Globar(this);
        //this.customHandler = new CustomHandler(this);

        this.viewPager = findViewById(R.id.photos_viewpager);
        this.info_backgroundV = findViewById(R.id.info_backgroundV);
        this.info_backgroundV.setBackgroundColor(this.g.info_color[0]);
//        this.closeBt = findViewById(R.id.info_closeBt);
//        this.closeBt.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                finish();
//                overridePendingTransition(0,R.anim.anim_slide_out_bottom_login);
//            }
//        });
        this.viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                info_backgroundV.setBackgroundColor(g.info_color[i]);
            }

            @Override
            public void onPageScrollStateChanged(int i) {
                Log.d("onPageStateChanged",""+i);
            }
        });
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
        this.viewReset();
        this.g = new Globar(this);
        //메인 배너 이미지 다운로드 받기
        this.updateInfoImage();

    }

    public void updateInfoImage() {
        Adapter a = new Adapter(this.info_imgs, this);
        this.viewPager.setAdapter(a);

        TabLayout tabLayout = findViewById(R.id.tab_layout);
        tabLayout.setupWithViewPager(this.viewPager, true);
    }

    class Adapter extends PagerAdapter {

        Context context;
        //ArrayList<InfoDTO> obj;
        int[] obj;
        private Globar g;

        Adapter(int[] res, Context context) {
            obj = res;
            this.context = context;
            this.g = new Globar(context);
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {

            View view = null;

            //InfoDTO r = this.obj.get(position);

            LayoutInflater inflater = LayoutInflater.from(context);
            view = inflater.inflate(R.layout.info_pager_adapter, container, false);
            LinearLayout infoBack = view.findViewById(R.id.info_page);
            infoBack.setBackgroundColor(g.info_color[position]);
            ImageView imageView = (ImageView) view.findViewById(R.id.info_img);
            ImageView txt = (ImageView) view.findViewById(R.id.info_txt);
            imageView.setImageResource(info_imgs[position]);
            txt.setImageResource(info_txt[position]);



            container.addView(view);

            return view;
        }

        public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
            return view == o;
        }


        public int getCount() {
            return obj.length;
        }

        @Override
        public int getItemPosition(@NonNull Object object) {
            return super.getItemPosition(object);
        }

        @Override
        public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
            container.removeView((View) object);
        }
    }
}
