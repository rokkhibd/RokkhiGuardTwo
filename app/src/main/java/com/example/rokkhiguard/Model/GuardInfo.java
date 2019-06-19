package com.example.rokkhiguard.Model;

import java.util.List;

public class GuardInfo {

    private String g_name;
    private String g_oic;
    private String g_thumb;
    private String is_activated;
    private String g_com;
    private String g_phone;
    private String g_userId;
    private String g_bday;
    private String g_address;
    private String g_joining;
    private String g_pass;
    private String g_nid;
    private List<String> g_array;


    public GuardInfo() {

    }

    public GuardInfo(String g_name, String g_oic, String g_thumb, String is_activated,
                     String g_com, String g_phone, String g_userId, String g_bday, String g_address,
                     String g_joining, String g_pass, String g_nid,List<String> g_array) {

        this.g_name = g_name;
        this.g_oic = g_oic;
        this.g_thumb = g_thumb;
        this.is_activated = is_activated;
        this.g_com = g_com;
        this.g_phone = g_phone;
        this.g_userId = g_userId;
        this.g_bday = g_bday;
        this.g_address = g_address;
        this.g_joining = g_joining;
        this.g_pass = g_pass;
        this.g_nid = g_nid;
        this.g_array = g_array;
    }

    public String getG_name() {
        return g_name;
    }

    public void setG_name(String g_name) {
        this.g_name = g_name;
    }

    public String getG_oic() {
        return g_oic;
    }

    public void setG_oic(String g_oic) {
        this.g_oic = g_oic;
    }

    public String getG_thumb() {
        return g_thumb;
    }

    public void setG_thumb(String g_thumb) {
        this.g_thumb = g_thumb;
    }

    public String getIs_activated() {
        return is_activated;
    }

    public void setIs_activated(String is_activated) {
        this.is_activated = is_activated;
    }

    public String getG_com() {
        return g_com;
    }

    public void setG_com(String g_com) {
        this.g_com = g_com;
    }

    public String getG_phone() {
        return g_phone;
    }

    public void setG_phone(String g_phone) {
        this.g_phone = g_phone;
    }

    public String getG_userId() {
        return g_userId;
    }

    public void setG_userId(String g_userId) {
        this.g_userId = g_userId;
    }

    public String getG_bday() {
        return g_bday;
    }

    public void setG_bday(String g_bday) {
        this.g_bday = g_bday;
    }

    public String getG_address() {
        return g_address;
    }

    public void setG_address(String g_address) {
        this.g_address = g_address;
    }

    public String getG_joining() {
        return g_joining;
    }

    public void setG_joining(String g_joining) {
        this.g_joining = g_joining;
    }

    public String getG_pass() {
        return g_pass;
    }

    public void setG_pass(String g_pass) {
        this.g_pass = g_pass;
    }

    public String getG_nid() {
        return g_nid;
    }

    public void setG_nid(String g_nid) {
        this.g_nid = g_nid;
    }

    public List<String> getG_array() {
        return g_array;
    }

    public void setG_array(List<String> g_array) {
        this.g_array = g_array;
    }
}
