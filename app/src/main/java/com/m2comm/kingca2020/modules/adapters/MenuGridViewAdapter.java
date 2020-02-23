package com.m2comm.kingca2020.modules.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.m2comm.kingca2020.R;
import com.m2comm.kingca2020.modules.common.Custom_SharedPreferences;
import com.m2comm.kingca2020.modules.common.Globar;

public class MenuGridViewAdapter extends BaseAdapter {

    private Integer[] mainIcon = {
            R.drawable.menu_member1,
            R.drawable.menu_member2,
            R.drawable.menu_member3,
    };

    private String[] names = {
            "Logine",
            "My Schedule",
            "My Memo",
    };

    private Globar g;
    private Context c;
    private LayoutInflater inflater;
    private Custom_SharedPreferences csp;


    public MenuGridViewAdapter(Context c, LayoutInflater inflater) {
        this.c = c;
        this.g = new Globar(c);
        this.inflater = inflater;
        this.csp = new Custom_SharedPreferences(c);
    }

    @Override
    public int getCount() {
        return this.names.length;
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

            convertView = this.inflater.inflate(R.layout.menu_gridview_child,parent,false);
            ImageView imageView = (ImageView)convertView.findViewById(R.id.menu_gridview_img);
            TextView textView = (TextView)convertView.findViewById(R.id.menu_gridview_text);

            ViewGroup.LayoutParams param = convertView.getLayoutParams();
            if(param == null) {
                param = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            }
            param.width = this.g.w(100);
            param.height = this.g.h(100);
            convertView.setLayoutParams(param);

            if ( position == 2 && csp.getValue("isLogin",false)) {
                textView.setText("My Memo");
                imageView.setImageResource(R.drawable.menu_member4);
            } else {
                textView.setText(this.names[position]);
                imageView.setImageResource(this.mainIcon[position]);
            }
            convertView.setBackgroundResource(R.color.main_color_darkblue);
        }

        return convertView;
    }
}
