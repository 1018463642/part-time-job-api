package com.proj.api.user.gson;

import com.proj.api.exception.error.Reason;

/**
 * Created by jangitlau on 2017/11/3.
 */
public class AuthorizationRetGson {
    private int ret_code=0;
    private String reason= Reason.getReason(0);
    private InnerData data;

    public AuthorizationRetGson(String _sUsername,int _sUserId,String sPreToken) {
        this.data=new InnerData();
        this.data.setUsername(_sUsername);
        this.data.setUser_id(_sUserId);
        this.data.setPre_token(sPreToken);
    }

    public int getRet_code() {
        return ret_code;
    }

    public String getReason() {
        return reason;
    }

    public InnerData getData() {
        return data;
    }

    public class InnerData{
        String username;
        int user_id;
        String pre_token;

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public int getUser_id() {
            return user_id;
        }

        public void setUser_id(int user_id) {
            this.user_id = user_id;
        }

        public String getPre_token() {
            return pre_token;
        }

        public void setPre_token(String pre_token) {
            this.pre_token = pre_token;
        }
    }
}
