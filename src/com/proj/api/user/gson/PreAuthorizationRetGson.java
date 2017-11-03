package com.proj.api.user.gson;

import com.proj.api.exception.error.Reason;

/**
 * Created by jangitlau on 2017/11/3.
 */
public class PreAuthorizationRetGson {
    private int ret_code=0;
    private String reason= Reason.getReason(0);
    private InnerData data;

    public PreAuthorizationRetGson(String _sUsername,String _sKey) {
        this.data=new InnerData();
        this.data.setUsername(_sUsername);
        this.data.setKey(_sKey);
    }

    public class InnerData{
        String username;
        String key;

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }
    }


}
