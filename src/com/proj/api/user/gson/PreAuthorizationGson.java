package com.proj.api.user.gson;

/**
 * Created by jangitlau on 2017/11/2.
 */
public class PreAuthorizationGson {
    public final static int iSessionExpire = 60;
    public final static String sessionPrefix = "user_preauth_";

    private int iId;
    private String sUserName;
    private String sTranPassword;
    private String sAuthPassword;
    private String sRandomKey;
    private int iType;
    private int iAuthority;
    private int iStatus;

    public int getiId() {
        return iId;
    }

    public void setiId(int iId) {
        this.iId = iId;
    }

    public String getsUserName() {
        return sUserName;
    }

    public void setsUserName(String sUserName) {
        this.sUserName = sUserName;
    }

    public String getsTranPassword() {
        return sTranPassword;
    }

    public void setsTranPassword(String sTranPassword) {
        this.sTranPassword = sTranPassword;
    }

    public String getsAuthPassword() {
        return sAuthPassword;
    }

    public void setsAuthPassword(String sAuthPassword) {
        this.sAuthPassword = sAuthPassword;
    }

    public String getiRandomKey() {
        return sRandomKey;
    }

    public void setiRandomKey(String sRandomKey) {
        this.sRandomKey = sRandomKey;
    }

    public int getiType() {
        return iType;
    }

    public void setiType(int iType) {
        this.iType = iType;
    }

    public int getiAuthority() {
        return iAuthority;
    }

    public void setiAuthority(int iAuthority) {
        this.iAuthority = iAuthority;
    }

    public int getiStatus() {
        return iStatus;
    }

    public void setiStatus(int iStatus) {
        this.iStatus = iStatus;
    }

    public PreAuthorizationGson(int iId, String sUserName, String sTranPassword
            , String sAuthPassword, String sRandomKey, int iType, int iAuthority, int iStatus) {
        this.iId = iId;
        this.sUserName = sUserName;
        this.sTranPassword = sTranPassword;
        this.sAuthPassword = sAuthPassword;
        this.sRandomKey = sRandomKey;
        this.iType = iType;
        this.iAuthority = iAuthority;
        this.iStatus = iStatus;
    }
}
