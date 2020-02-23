package com.m2comm.kingca2020.models;

public class NoticeDTO {

    int sid;
    String subject;
    String newNotice;
    String signdate;


    public NoticeDTO(int sid, String subject, String newNotice, String signdate) {
        this.sid = sid;
        this.subject = subject;
        this.newNotice = newNotice;
        this.signdate = signdate;
    }

    public void setSid(int sid) {
        this.sid = sid;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public void setNewNotice(String newNotice) {
        this.newNotice = newNotice;
    }

    public void setSigndate(String signdate) {
        this.signdate = signdate;
    }

    public int getSid() {
        return sid;
    }

    public String getSubject() {
        return subject;
    }

    public String getNewNotice() {
        return newNotice;
    }

    public String getSigndate() {
        return signdate;
    }
}
