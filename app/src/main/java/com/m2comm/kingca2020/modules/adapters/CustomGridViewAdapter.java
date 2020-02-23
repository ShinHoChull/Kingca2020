package com.m2comm.kingca2020.modules.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.m2comm.kingca2020.R;
import com.m2comm.kingca2020.modules.common.Globar;

public class CustomGridViewAdapter extends BaseAdapter {

    private Integer[] mainIcon = {
            R.drawable.main_menu1,
            R.drawable.main_menu2,
            R.drawable.main_menu3,
            R.drawable.main_menu4,
            R.drawable.main_menu5,
            R.drawable.main_menu6,
            R.drawable.main_menu7,
            R.drawable.main_menu8,
            R.drawable.main_menu9,
    };

    private String[] names = {
            "KINGCA",
            "Program",
            "Abstract",
            "Speakers",
            "Venue\nFloor Plan",
            "General\nInformation",
            "Photo Gallery",
            "Sponsors &\nBooth",
            "Notice"
    };

    private Globar g;
    private Context c;
    private LayoutInflater inflater;


    public CustomGridViewAdapter(Context c, LayoutInflater inflater) {
        this.c = c;
        this.g = new Globar(c);
        this.inflater = inflater;
    }

    @Override
    public int getCount() {
        return this.mainIcon.length;
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if ( convertView == null ) {

            convertView = this.inflater.inflate(R.layout.main_gridview_child,parent,false);
            ImageView imageView = (ImageView)convertView.findViewById(R.id.main_gridview_img);
            TextView textView = (TextView)convertView.findViewById(R.id.main_gridview_text);

            ViewGroup.LayoutParams param = convertView.getLayoutParams();
            if(param == null) {
                param = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            }

            param.height = parent.getHeight() / 3 - 2;


            convertView.setLayoutParams(param);

            textView.setText(this.names[position]);
            imageView.setImageResource(this.mainIcon[position]);
            convertView.setBackgroundResource(R.color.main_color_white);
        }

        return convertView;
    }
}
