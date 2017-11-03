package com.proj.api.user.gson;

import com.proj.api.exception.error.Reason;

/**
 * Created by jangitlau on 2017/11/3.
 */
public class RegistrationRetGson {
    private int err_code=0;
    private String reason= Reason.getReason(0);
    private InnerData data;

    public RegistrationRetGson(String pre_token, int user_id, int type) {
        this.data=new InnerData(pre_token,user_id,type);
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

    public class InnerData {
        public void setPre_token(String pre_token) {
            this.pre_token = pre_token;
        }

        public void setUser_id(int user_id) {
            this.user_id = user_id;
        }

        public void setType(int type) {
            this.type = type;
        }

        private String pre_token;
        private int user_id;
        private int type;

        public InnerData(String pre_token, int user_id, int type) {
            this.pre_token = pre_token;
            this.user_id = user_id;
            this.type = type;
        }
    }

}
