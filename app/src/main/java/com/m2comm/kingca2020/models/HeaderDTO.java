package com.m2comm.kingca2020.models;

public class HeaderDTO {

    private String url;
    private String title;

    public HeaderDTO(String url, String title) {
        this.url = url;
        this.title = title;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public String getTitle() {
        return title;
    }
}
