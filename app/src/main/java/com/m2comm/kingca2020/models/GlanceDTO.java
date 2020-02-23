package com.m2comm.kingca2020.models;


import java.io.Serializable;
import java.util.ArrayList;

public class GlanceDTO implements Serializable {
    int day_type;
    int bottom_menu;
    String session_topmenu_bg;
    String session_topmenu_font;
    String menu_bg;
    String menu_bg_on;
    String menu_font;
    String menu_font_on;
    String session_day_bg;
    String tab;
    String mon;

    ArrayList<GlanceChild> GlanceList;

    public GlanceDTO(int day_type, int bottom_menu, String session_topmenu_bg, String session_topmenu_font, String menu_bg, String menu_bg_on, String menu_font, String menu_font_on, String session_day_bg, String tab, String mon, ArrayList<GlanceChild> glanceList) {
        this.day_type = day_type;
        this.bottom_menu = bottom_menu;
        this.session_topmenu_bg = session_topmenu_bg;
        this.session_topmenu_font = session_topmenu_font;
        this.menu_bg = menu_bg;
        this.menu_bg_on = menu_bg_on;
        this.menu_font = menu_font;
        this.menu_font_on = menu_font_on;
        this.session_day_bg = session_day_bg;
        this.tab = tab;
        this.mon = mon;
        GlanceList = glanceList;
    }

    public void setDay_type(int day_type) {
        this.day_type = day_type;
    }

    public void setBottom_menu(int bottom_menu) {
        this.bottom_menu = bottom_menu;
    }

    public void setSession_topmenu_bg(String session_topmenu_bg) {
        this.session_topmenu_bg = session_topmenu_bg;
    }

    public void setSession_topmenu_font(String session_topmenu_font) {
        this.session_topmenu_font = session_topmenu_font;
    }

    public void setMenu_bg(String menu_bg) {
        this.menu_bg = menu_bg;
    }

    public void setMenu_bg_on(String menu_bg_on) {
        this.menu_bg_on = menu_bg_on;
    }

    public void setMenu_font(String menu_font) {
        this.menu_font = menu_font;
    }

    public void setMenu_font_on(String menu_font_on) {
        this.menu_font_on = menu_font_on;
    }

    public void setSession_day_bg(String session_day_bg) {
        this.session_day_bg = session_day_bg;
    }

    public void setTab(String tab) {
        this.tab = tab;
    }

    public void setMon(String mon) {
        this.mon = mon;
    }

    public void setGlanceList(ArrayList<GlanceChild> glanceList) {
        GlanceList = glanceList;
    }

    public int getDay_type() {
        return day_type;
    }

    public int getBottom_menu() {
        return bottom_menu;
    }

    public String getSession_topmenu_bg() {
        return session_topmenu_bg;
    }

    public String getSession_topmenu_font() {
        return session_topmenu_font;
    }

    public String getMenu_bg() {
        return menu_bg;
    }

    public String getMenu_bg_on() {
        return menu_bg_on;
    }

    public String getMenu_font() {
        return menu_font;
    }

    public String getMenu_font_on() {
        return menu_font_on;
    }

    public String getSession_day_bg() {
        return session_day_bg;
    }

    public String getTab() {
        return tab;
    }

    public String getMon() {
        return mon;
    }

    public ArrayList<GlanceChild> getGlanceList() {
        return GlanceList;
    }
}
