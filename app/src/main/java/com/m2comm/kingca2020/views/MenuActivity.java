package com.m2comm.kingca2020.views;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ExpandableListView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.m2comm.kingca2020.R;
import com.m2comm.kingca2020.modules.adapters.MenuGridViewAdapter;
import com.m2comm.kingca2020.modules.common.AnimatedExpandableListView;
import com.m2comm.kingca2020.modules.common.Custom_SharedPreferences;
import com.m2comm.kingca2020.modules.common.Globar;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MenuActivity extends Activity implements View.OnClickListener, AdapterView.OnItemClickListener {

    private AnimatedExpandableListView listView;
    private ExampleAdapter adapter;
    private ArrayList<GroupItem> header;

    private GridView gridview;
    private Globar g;

    private LinearLayout boothBt , pieH1 ;
    private ImageView closeBt, loginBt, setting, menu_homeImg;
    private TextView loginText, menu_homeText;

    private Custom_SharedPreferences csp;

    private void listenerRegister() {
        this.gridview.setOnItemClickListener(this);
        this.closeBt.setOnClickListener(this);
        this.loginBt.setOnClickListener(this);
        this.loginText.setOnClickListener(this);
        this.setting.setOnClickListener(this);
        this.boothBt.setOnClickListener(this);
        this.menu_homeText.setOnClickListener(this);
        this.menu_homeImg.setOnClickListener(this);
    }

    private void init() {
        this.g = new Globar(this);
        this.csp = new Custom_SharedPreferences(this);

        this.gridview = findViewById(R.id.menu_grid);
        this.closeBt = findViewById(R.id.menu_closeBt);
        this.loginBt = findViewById(R.id.menu_login_img);
        this.loginText = findViewById(R.id.menu_login_text);
        this.setting = findViewById(R.id.menu_login_setting);
        this.boothBt = findViewById(R.id.boothBt);
        this.menu_homeImg = findViewById(R.id.menu_homeImg);
        this.menu_homeText = findViewById(R.id.menu_homeText);
        this.pieH1 = findViewById(R.id.pieH1);



        this.listenerRegister();

        this.header = new ArrayList<>();

        header.add(new GroupItem(R.drawable.menu_ic1, "KINGCA",
                new ArrayList<ChildItem>(Arrays.asList(new ChildItem("- Welcome"), new ChildItem("- Overview"),
                        new ChildItem("- Organizing Committee"), new ChildItem("- Past KINGCA"), new ChildItem("- KGCA")))));

        header.add(new GroupItem(R.drawable.menu_ic2, "Program",
                new ArrayList<ChildItem>(Arrays.asList(new ChildItem("- Program at a glance"), new ChildItem("- Program by Day")
                        , new ChildItem("- Program by Session"), new ChildItem("- My Schedule"), new ChildItem("- Now")))));

        header.add(new GroupItem(R.drawable.menu_ic3, "Abstract",
                new ArrayList<ChildItem>()));

        header.add(new GroupItem(R.drawable.menu_ic4, "Speakers",
                new ArrayList<ChildItem>()));

        header.add(new GroupItem(R.drawable.menu_ic5, "Venue / Floor Plan",
                new ArrayList<ChildItem>(Arrays.asList(new ChildItem("- Floor Plan"), new ChildItem("- Venue")
                        , new ChildItem("- Transportation")))));

        header.add(new GroupItem(R.drawable.menu_ic6, "General Information",
                new ArrayList<ChildItem>(Arrays.asList(new ChildItem("- Presentation Guideline"), new ChildItem("- Education Session")
                        , new ChildItem("- Research Group Meeting"), new ChildItem("- Luncheon Symposium / Seminar")))));

        header.add(new GroupItem(R.drawable.menu_ic7, "Photo Gallery",
                new ArrayList<ChildItem>()));

        header.add(new GroupItem(R.drawable.menu_ic8, "Sponsors/ Booth",
                new ArrayList<ChildItem>(Arrays.asList(new ChildItem("- Sponsors"), new ChildItem("- Booth")))));

        header.add(new GroupItem(R.drawable.menu_ic9, "Notice",
                new ArrayList<ChildItem>()));

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        this.init();

        MenuGridViewAdapter mga = new MenuGridViewAdapter(this, getLayoutInflater());
        this.gridview.setAdapter(mga);

        this.adapter = new ExampleAdapter(this);
        this.adapter.setData(this.header);

        listView = findViewById(R.id.listView);
        listView.setAdapter(adapter);

        // In order to show animations, we need to use a custom click handler
        // for our ExpandableListView.
        listView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            int lastClickedPosition = -1;
            GroupHolder oldHolder = null;

            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, final int groupPosition, long id) {

                GroupHolder holder = (GroupHolder) v.getTag();

                if (header.get(groupPosition).items.size() <= 0) {
                    moveView(groupPosition, 0);
                    return true;
                }
                if (listView.isGroupExpanded(groupPosition)) {
                    listView.collapseGroupWithAnimation(groupPosition);
                    changefoldImg(R.string.plus, holder);
                } else {
                    changefoldImg(R.string.minus, holder);
                    listView.collapseGroupWithAnimation(lastClickedPosition);
                    if (oldHolder != null && groupPosition != lastClickedPosition) {
                        changefoldImg(R.string.plus, oldHolder);
                    }
                    listView.expandGroupWithAnimation(groupPosition);
                }

                lastClickedPosition = groupPosition;
                oldHolder = (GroupHolder) v.getTag();
                return true;
            }
        });

        listView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                moveView(groupPosition, childPosition);
                return false;
            }
        });


        //로그인 확인.
        if (csp.getValue("isLogin", false)) {
            this.loginText.setVisibility(View.GONE);
            this.loginBt.setVisibility(View.GONE);
            this.setting.setVisibility(View.VISIBLE);
        }
    }

    private void changefoldImg(int text, GroupHolder holder) {
        Typeface fontAwsome = Typeface.createFromAsset(getAssets(), "fa_solid_900.ttf");
        holder.foldImg.setTypeface(fontAwsome);
        holder.foldImg.setText(text);
        holder.foldImg.setTextColor(getResources().getColor(R.color.main_color_gray));
    }

    private static class GroupItem {
        int img;
        String title;
        List<ChildItem> items;

        public GroupItem(int img, String title, List<ChildItem> items) {
            this.img = img;
            this.title = title;
            this.items = items;
        }
    }

    private static class ChildItem {
        String title;

        public ChildItem(String title) {
            this.title = title;
        }
    }

    private static class ChildHolder {
        TextView title;
        TextView hint;
    }

    private static class GroupHolder {
        TextView title;
        ImageView headerIconImg;
        TextView foldImg;
    }

    /**
     * Adapter for our list of {@link GroupItem}s.
     */
    private class ExampleAdapter extends AnimatedExpandableListView.AnimatedExpandableListAdapter {
        private LayoutInflater inflater;
        private List<GroupItem> items;

        public ExampleAdapter(Context context) {
            inflater = LayoutInflater.from(context);
        }

        public void setData(List<GroupItem> items) {
            this.items = items;
        }

        @Override
        public ChildItem getChild(int groupPosition, int childPosition) {
            return items.get(groupPosition).items.get(childPosition);
        }

        @Override
        public long getChildId(int groupPosition, int childPosition) {
            return childPosition;
        }

        @Override
        public View getRealChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
            ChildHolder holder;
            ChildItem item = getChild(groupPosition, childPosition);
            if (convertView == null) {
                holder = new ChildHolder();
                convertView = inflater.inflate(R.layout.menu_list_item, parent, false);
                holder.title = (TextView) convertView.findViewById(R.id.menu_child_textTitle);
                convertView.setTag(holder);
            } else {
                holder = (ChildHolder) convertView.getTag();
            }

            holder.title.setText(item.title);
            return convertView;
        }

        @Override
        public int getRealChildrenCount(int groupPosition) {
            return items.get(groupPosition).items.size();
        }

        @Override
        public GroupItem getGroup(int groupPosition) {
            return items.get(groupPosition);
        }

        @Override
        public int getGroupCount() {
            return items.size();
        }

        @Override
        public long getGroupId(int groupPosition) {
            return groupPosition;
        }

        @Override
        public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {

            GroupHolder holder;
            GroupItem item = getGroup(groupPosition);

            if (convertView == null) {
                holder = new GroupHolder();
                convertView = inflater.inflate(R.layout.menu_group_item, parent, false);
                holder.headerIconImg = convertView.findViewById(R.id.menuImg);
                holder.foldImg = convertView.findViewById(R.id.foldImg);
                holder.title = convertView.findViewById(R.id.textTitle);
                convertView.setTag(holder);
            } else {
                holder = (GroupHolder) convertView.getTag();
            }

            holder.headerIconImg.setImageResource(item.img);
            holder.title.setText(item.title);

            if (getRealChildrenCount(groupPosition) <= 0) {
                holder.foldImg.setVisibility(View.INVISIBLE);
            } else {
                holder.foldImg.setVisibility(View.VISIBLE);
            }

            if (listView.isGroupExpanded(groupPosition)) {
                changefoldImg(R.string.minus, holder);
            } else {
                changefoldImg(R.string.plus, holder);
            }

            return convertView;
        }

        @Override
        public boolean hasStableIds() {
            return true;
        }

        @Override
        public boolean isChildSelectable(int arg0, int arg1) {
            return true;
        }

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.menu_homeImg:
            case R.id.menu_homeText:
                Intent main = new Intent(this, MainActivity.class);
                main.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivity(main);
                finish();
                break;

            case R.id.menu_closeBt:
                finish();
                overridePendingTransition(0, R.anim.anim_slide_out_left);
                break;

            case R.id.menu_login_img:
            case R.id.menu_login_text:
                Intent content = new Intent(this, LoginActivity.class);
                startActivity(content);
                overridePendingTransition(R.anim.anim_slide_in_bottom_login, 0);
                finish();
                break;
            case R.id.menu_login_setting:
                Intent setting = new Intent(this, SettingActivity.class);
                startActivity(setting);
                overridePendingTransition(R.anim.anim_slide_in_right, R.anim.anim_slide_out_left);
                finish();
                break;
            case R.id.boothBt:
                Log.d("barcode=",this.g.urls.get("boothEvent")+"?barcode="+csp.getValue("reg_num",""));
                Intent booth = new Intent(this, ContentsActivity.class);
                booth.putExtra("content", true);
                booth.putExtra("paramUrl", this.g.urls.get("boothEvent")+"?barcode="+csp.getValue("reg_num",""));
                startActivity(booth);
                overridePendingTransition(R.anim.anim_slide_in_right, R.anim.anim_slide_out_left);
                finish();
                break;
        }
    }

    public boolean isLogin() {
        return this.csp.getValue("isLogin", false);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent content = new Intent(this, ContentsActivity.class);

        switch (position) {
            case 0:
                //MySchedule
                if (!isLogin()) {
                    this.g.loginAlertMessage("Alert", "Do you want to sign in?", this);
                    return;
                }
                content.putExtra("paramUrl", this.g.linkUrl[1][3]);
                break;
            case 1:
                if (!isLogin()) {
                    this.g.loginAlertMessage("Alert", "Do you want to sign in?", this);
                    return;
                }
                content.putExtra("content", true);
                content.putExtra("paramUrl", this.g.urls.get("attendance") + "?id=" + csp.getValue("userid", ""));

                break;
            case 2:
                if (isLogin()) {
                    content.putExtra("paramUrl", this.g.urls.get("myMemo"));
                    break;
                } else {
                    //Find Acount
//                    Intent find = new Intent(this, FindActivity.class);
//                    startActivity(find);
//                    overridePendingTransition(R.anim.anim_slide_in_right, R.anim.anim_slide_out_left);
//                    finish();
                    content.putExtra("content", true);
                    content.putExtra("paramUrl", this.g.urls.get("findAccount"));
                    break;
                }
        }

        startActivity(content);
        overridePendingTransition(R.anim.anim_slide_in_right, R.anim.anim_slide_out_left);

        finish();

    }

    private void moveView(int groupPostion, int childPosition) {
        int[] noContents = {1, 2, 3, 6, 8};

        if (groupPostion == 6) {
            Intent photo = new Intent(this, PhotoActivity.class);
            startActivity(photo);
            overridePendingTransition(R.anim.anim_slide_in_right, R.anim.anim_slide_out_left);
            finish();
            return;
        } else if (groupPostion == 1 && childPosition == 0) {
            Intent setting = new Intent(this, GlanceActivity.class);
            startActivity(setting);
            overridePendingTransition(R.anim.anim_slide_in_right, R.anim.anim_slide_out_left);
            finish();
            return;
        } else if (groupPostion == 2) {
            if (!isLogin()) {
                this.g.loginAlertMessage("Alert", "Do you want to sign in?", this);
                return;
            }
        }

        Intent content = new Intent(this, ContentsActivity.class);
        if (groupPostion != 1 && groupPostion != 2 && groupPostion != 3 && groupPostion != 8) {
            content.putExtra("content", true);
        }

        content.putExtra("paramUrl", this.g.linkUrl[groupPostion][childPosition]);
        content.putExtra("title", this.header.get(groupPostion).title);
        startActivity(content);
        overridePendingTransition(R.anim.anim_slide_in_right, R.anim.anim_slide_out_left);
        finish();
    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        finish();
        overridePendingTransition(0, R.anim.anim_slide_out_left);
    }
}
