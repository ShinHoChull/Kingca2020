package com.m2comm.kingca2020.modules.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.m2comm.kingca2020.R;
import com.m2comm.kingca2020.models.MainGetPhotoDTO;
import com.m2comm.kingca2020.modules.common.Globar;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class PhotoGridAdapter extends BaseAdapter {

    private ArrayList<MainGetPhotoDTO> items;
    private LayoutInflater inflater;
    private Globar g;
    private Context c;

    public PhotoGridAdapter(Context c , LayoutInflater inflater , ArrayList<MainGetPhotoDTO> items) {
        this.c = c;
        this.inflater = inflater;
        this.items = items;
        this.g = new Globar(c);
    }

    @Override
    public int getCount() {
        return this.items.size();
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

            MainGetPhotoDTO r = items.get(position);

            convertView = this.inflater.inflate(R.layout.photo_item,parent,false);

            ImageView img = convertView.findViewById(R.id.photo_grid_img);
            img.setScaleType(ImageView.ScaleType.CENTER_CROP);

            Picasso.get().load(this.g.mainUrl+r.getUrl()).resize(this.g.w(100),0).error(R.mipmap.ic_launcher).into(img);

            ViewGroup.LayoutParams param = convertView.getLayoutParams();
            if(param == null) {
                param = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            }

            param.width = this.g.w(119);
            param.height = this.g.h(119);
            convertView.setLayoutParams(param);

        }

        return convertView;
    }


}
