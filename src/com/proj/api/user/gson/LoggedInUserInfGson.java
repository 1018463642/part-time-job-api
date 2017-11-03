package com.proj.api.user.gson;

/**
 * Created by jangitlau on 2017/11/3.
 */
public class LoggedInUserInfGson {
    private int iId;
    private String sUserName;
    private int iType;
    private int iAuthority;
    private int iStatus;
    private String sToken;
    private long lLoginTime;

    public LoggedInUserInfGson(int iId, String sUserName, int iType, int iAuthority, int iStatus, String sToken, long lLoginTime) {
        this.iId = iId;
        this.sUserName = sUserName;
        this.iType = iType;
        this.iAuthority = iAuthority;
        this.iStatus = iStatus;
        this.sToken = sToken;
        this.lLoginTime = lLoginTime;
    }

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

    public String getsToken() {
        return sToken;
    }

    public void setsToken(String sToken) {
        this.sToken = sToken;
    }

    public long getlLoginTime() {
        return lLoginTime;
    }

    public void setlLoginTime(long lLoginTime) {
        this.lLoginTime = lLoginTime;
    }
}
