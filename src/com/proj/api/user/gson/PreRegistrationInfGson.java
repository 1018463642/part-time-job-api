package com.proj.api.user.gson;

/**
 * Created by jangitlau on 2017/11/3.
 */
public class PreRegistrationInfGson {
    public final static int iSessionExpire = 300;
    public final static String sessionPrefix = "user_prereg_";

    private String username;
    private String phonenum;
    private String rand_str;

    public PreRegistrationInfGson(String username, String phonenum, String rand_str) {
        this.username = username;
        this.phonenum = phonenum;
        this.rand_str = rand_str;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPhonenum() {
        return phonenum;
    }

    public void setPhonenum(String phonenum) {
        this.phonenum = phonenum;
    }

    public String getRand_str() {
        return rand_str;
    }

    public void setRand_str(String rand_str) {
        this.rand_str = rand_str;
    }
}
