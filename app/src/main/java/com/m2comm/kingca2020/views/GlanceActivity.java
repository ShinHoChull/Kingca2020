package com.m2comm.kingca2020.views;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.m2comm.kingca2020.R;
import com.m2comm.kingca2020.modules.common.ChromeclientPower;
import com.m2comm.kingca2020.modules.common.Custom_SharedPreferences;
import com.m2comm.kingca2020.modules.common.Download;
import com.m2comm.kingca2020.modules.common.Download_PDFViewerActivity;
import com.m2comm.kingca2020.modules.common.Globar;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;

public class GlanceActivity extends AppCompatActivity implements View.OnClickListener {


    private Custom_SharedPreferences csp;
    private ChromeclientPower chromeclient;
    private Globar g;
    private WebView wv;
    private RelativeLayout t1b,t2b,t3b;
    private TextView t1t,t2t,t3t;
    private FrameLayout t1,t2,t3;
    private String defaultTab;

    private LinearLayout bBt1,bBt2,bBt3,bBt4,defaultBt;


    public void init() {
        this.g = new Globar(this);
        this.csp = new Custom_SharedPreferences(this);
        this.g.addFragment_Content_TopV(this);
        this.g.addFragment_Sub_TopV(this,"Program at a glance");

        this.wv = findViewById(R.id.g_Webview);

        this.t1b = findViewById(R.id.tab1_backV);
        this.t2b = findViewById(R.id.tab2_backV);
        this.t3b = findViewById(R.id.tab3_backV);

        this.t1t = findViewById(R.id.tab1_text);
        this.t2t = findViewById(R.id.tab2_text);
        this.t3t = findViewById(R.id.tab3_text);

        this.t1 = findViewById(R.id.tab1);
        this.t2 = findViewById(R.id.tab2);
        this.t3 = findViewById(R.id.tab3);

        this.bBt1 = findViewById(R.id.g_now);
        this.bBt2 = findViewById(R.id.g_Program);
        this.bBt3 = findViewById(R.id.g_schedule);
        this.bBt4 = findViewById(R.id.g_bySession);

        this.defaultBt = findViewById(R.id.default_clickV);


        this.chromeclient = new ChromeclientPower(this,this,this.wv);

        this.wv.setWebViewClient(new WebviewCustomClient());
        this.wv.getSettings().setUseWideViewPort(true);
        this.wv.getSettings().setJavaScriptEnabled(true);
        this.wv.getSettings().setLoadWithOverviewMode(true);
        this.wv.getSettings().setDefaultTextEncodingName("utf-8");
        this.wv.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        this.wv.getSettings().setSupportMultipleWindows(false);
        this.wv.getSettings().setDomStorageEnabled(true);
        this.wv.getSettings().setBuiltInZoomControls(true);
        this.wv.getSettings().setDisplayZoomControls(false);
        this.wv.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);


        this.listenerRegister();
    }

    private void listenerRegister() {
        this.t1.setOnClickListener(this);
        this.t2.setOnClickListener(this);
        this.t3.setOnClickListener(this);

        this.bBt1.setOnClickListener(this);
        this.bBt2.setOnClickListener(this);
        this.bBt3.setOnClickListener(this);
        this.bBt4.setOnClickListener(this);

        this.defaultBt.setOnClickListener(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_glance);
        getWindow().setWindowAnimations(0);

        this.init();

        Date memDelStartDate1; // 삭제 시작일
        Date memDelStartDate2; // 삭제 시작일
        Date memDelStartDate3; // 삭제 시작일
        Date currentDate; // 현재날짜 Date
        String oTime = ""; // 현재날짜

        SimpleDateFormat mSimpleDateFormat = new SimpleDateFormat ( "yyyy-MM-dd", Locale.KOREA );
        Date currentTime = new Date();
        oTime = mSimpleDateFormat.format ( currentTime );
        try {
            memDelStartDate1 = mSimpleDateFormat.parse( "2019-04-11" );
            memDelStartDate2 = mSimpleDateFormat.parse( "2019-04-12" );
            memDelStartDate3 = mSimpleDateFormat.parse( "2019-04-13" );
            currentDate =  mSimpleDateFormat.parse( oTime );
            int compare1 = currentDate.compareTo( memDelStartDate1 ); // 날짜비교
            int compare2 = currentDate.compareTo( memDelStartDate2 );
            int compare3 = currentDate.compareTo( memDelStartDate3 );

            if ( compare1 == 0 ) {
                this.defaultTab = "39";
                this.defaultSelect(this.t1t,this.t1b);
            } else if ( compare2 == 0 ) {
                this.defaultTab = "40";
                this.defaultSelect(this.t2t,this.t2b);
            } else if ( compare3 == 0 ) {
                this.defaultTab = "41";
                this.defaultSelect(this.t3t,this.t3b);
            } else if (compare3 > 0 ) { // 행사 시작 후
                this.defaultTab = "41";
                this.defaultSelect(this.t3t,this.t3b);
            } else {
                this.defaultTab = "39";
                this.defaultSelect(this.t1t,this.t1b);
            }

        } catch (ParseException e) {
            Log.d("compare1",e.toString());
            this.defaultSelect(this.t1t,this.t1b);
        }
    }

    public String urlSetting(String paramUrl) {
        String deviceid = csp.getValue("deviceid","");
        String url = this.g.baseUrl + paramUrl;

        if (paramUrl.startsWith("http") || paramUrl.startsWith("https")) {
            url = paramUrl;
        }
        if ( paramUrl.contains("?") )url += "&";
        else url += "?";
        url += "deviceid="+deviceid+"&device=android";

        return url;
    }


    private class WebviewCustomClient extends WebViewClient
    {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url)
        {
            String[] urlCut = url.split("/");
            Log.d("NowUrl",url);
            if (url.startsWith(g.mainUrl) == false && url.startsWith(g.contentMainUrl) == false) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                startActivity(intent);
                return true;
            } else if ( g.extPDFSearch(urlCut[urlCut.length - 1]) ) {
                Intent content = new Intent(getApplicationContext(), Download_PDFViewerActivity.class);
                content.putExtra("url", url);
                content.addFlags(FLAG_ACTIVITY_NEW_TASK);
                startActivity(content);
                overridePendingTransition(R.anim.anim_slide_in_bottom_login,0);
                // view.loadUrl(doc);
                return true;
            } else if ( g.extSearch(urlCut[urlCut.length - 1]) ) { //기타 문서 Search
                new Download(url,getApplicationContext(),urlCut[urlCut.length - 1]);
                return true;
            } else if ( g.imgExtSearch(urlCut[urlCut.length - 1]) ) { //이미지 Search
                Intent content = new Intent(getApplicationContext(), PopWebviewActivity.class);
                content.putExtra("paramUrl", url);
                content.addFlags(FLAG_ACTIVITY_NEW_TASK);
                startActivity(content);
                overridePendingTransition(R.anim.anim_slide_in_bottom_login,0);
                return true;
            } else if (url.contains("glance_sub.php")) {
                Intent content = new Intent(getApplicationContext(),PopWebviewActivity.class);
                content.putExtra("paramUrl", url);
                content.addFlags(FLAG_ACTIVITY_NEW_TASK);
                startActivity(content);
                overridePendingTransition(R.anim.anim_slide_in_bottom_login,0);
                return true;
            } else if (urlCut[urlCut.length -1].equals("back.php")) {
                if (wv.canGoBack()) {
                    wv.goBack();
                } else {
                    finish();
                    overridePendingTransition(R.anim.anim_slide_in_left, R.anim.anim_slide_out_right);
                }
                return true;
            }
            return false;
        }

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);
            Log.d("onPageStarted",url);
        }

        @Override
        public void onLoadResource(WebView view, String url) {
            super.onLoadResource(view, url);
            Log.d("onLoadResource",url);
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            Log.d("onPageFinished",url);
        }

        @Override
        public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
            super.onReceivedError(view, errorCode, description, failingUrl);
            Toast.makeText(getApplicationContext(), "서버와 연결이 끊어졌습니다", Toast.LENGTH_SHORT ).show();
            view.loadUrl("about:blank");
        }
    }

    private void buttonReset () {
        this.t1t.setTextColor(Color.BLACK);
        this.t2t.setTextColor(Color.BLACK);
        this.t3t.setTextColor(Color.BLACK);
        this.t1b.setVisibility(View.INVISIBLE);
        this.t2b.setVisibility(View.INVISIBLE);
        this.t3b.setVisibility(View.INVISIBLE);
    }

    private void defaultSelect (TextView t , RelativeLayout r) {
        buttonReset();
        r.setVisibility(View.VISIBLE);
        t.setTextColor(Color.WHITE);
        this.wv.loadUrl(this.urlSetting(urlSetting("/session/glance.php?code="+this.g.code+"&tab="+this.defaultTab)));
    }

    @Override
    public void onClick(View v) {
        Intent content = new Intent(this, ContentsActivity.class);
        int position = 0;
        switch (v.getId()) {
            case R.id.tab1:
                this.defaultTab = "39";
                this.defaultSelect(t1t,t1b);
                return;
            case R.id.tab2:
                this.defaultTab = "40";
                this.defaultSelect(t2t,t2b);
                return;
            case R.id.tab3:
                this.defaultTab = "41";
                this.defaultSelect(t3t,t3b);
                return;

            case R.id.g_now:
                position = 4;
                break;
            case R.id.g_Program:
                position = 1;
                break;
            case R.id.g_schedule:
                position = 3;
                break;
            case R.id.g_bySession:
                position = 2;
                break;
            case R.id.default_clickV:
                this.defaultBt.setVisibility(View.GONE);
                return;
        }
        content.putExtra("paramUrl", this.g.linkUrl[1][position]);
        startActivity(content);

    }
}
