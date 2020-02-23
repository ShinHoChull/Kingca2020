package com.m2comm.kingca2020.models;

public class GlanceChild {

    String tab;
    String week;
    String day;

    public GlanceChild(String tab, String week, String day) {
        this.tab = tab;
        this.week = week;
        this.day = day;
    }

    public String getTab() {
        return tab;
    }

    public String getWeek() {
        return week;
    }

    public String getDay() {
        return day;
    }

    public void setTab(String tab) {
        this.tab = tab;
    }

    public void setWeek(String week) {
        this.week = week;
    }

    public void setDay(String day) {
        this.day = day;
    }
}
