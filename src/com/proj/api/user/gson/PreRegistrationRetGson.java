package com.proj.api.user.gson;

import com.proj.api.exception.error.Reason;

/**
 * Created by jangitlau on 2017/11/3.
 */
public class PreRegistrationRetGson {
    private int err_code=0;
    private String reason= Reason.getReason(0);
    private InnerData data;

    public PreRegistrationRetGson(String username, String key) {
        this.data = new InnerData(username,key);
    }

    public int getErr_code() {
        return err_code;
    }

    public String getReason() {
        return reason;
    }

    public InnerData getData() {
        return data;
    }


    public class InnerData{
        private String username;
        private String key;


        public InnerData(String username, String key) {
            this.username = username;
            this.key = key;
        }

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
