package com.m2comm.kingca2020.models;

public class LoginDTO {
    int sid;
    String reg_num;
    String id;
    String pw;

    public LoginDTO(int sid,String reg_num, String id, String pw) {
        this.sid = sid;
        this.reg_num = reg_num;
        this.id = id;
        this.pw = pw;
    }

    public String getReg_num() {
        return reg_num;
    }

    public void setReg_num(String reg_num) {
        this.reg_num = reg_num;
    }

    public void setSid(int sid) {
        this.sid = sid;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setPw(String pw) {
        this.pw = pw;
    }

    public int getSid() {
        return sid;
    }

    public String getId() {
        return id;
    }

    public String getPw() {
        return pw;
    }
}
