package com.m2comm.kingca2020.views;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.m2comm.kingca2020.R;
import com.m2comm.kingca2020.models.GlanceChild;
import com.m2comm.kingca2020.models.GlanceDTO;
import com.m2comm.kingca2020.modules.common.Globar;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class TestActivity extends AppCompatActivity {

    Globar g;
    GlanceDTO gDto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        this.g = new Globar(this);
        this.g.addFragmentTopV(this);

        AndroidNetworking.get(g.baseUrl+g.urls.get("getSet"))
                .addQueryParameter("code", g.code)
                .setPriority(Priority.LOW)
                .build().getAsJSONObject(new JSONObjectRequestListener() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    ArrayList<GlanceChild> arrayList = new ArrayList<>();

                    JSONArray jr =  response.getJSONArray("day");
                    for (int i = 0 , j = jr.length() ; i < j ; i ++) {
                        JSONObject obj = jr.getJSONObject(i);
                        GlanceChild r = new GlanceChild(obj.getString("tab"),
                                obj.getString("week"),
                                obj.getString("day"));
                        arrayList.add(r);
                    }

                    gDto = new GlanceDTO(response.getInt("day_type"),
                            response.getInt("bottom_menu"),
                            response.getString("session_topmenu_bg"),
                            response.getString("session_topmenu_font"),
                            response.getString("menu_bg"),
                            response.getString("menu_bg_on"),
                            response.getString("menu_font"),
                            response.getString("menu_font_on"),
                            response.getString("session_day_bg"),
                            response.getString("tab"),
                            response.getString("mon"),
                            arrayList
                            );
                } catch (JSONException e) {
                    Log.d("responseJOSNObjError=",e.toString());
                }
                addFragment();
            }

            @Override
            public void onError(ANError anError) {
                Log.d("responseERROR=",anError.toString());
            }
        });
    }

    public void addFragment() {
        g.addFragment_Glance_TopV(this, gDto);
    }

}