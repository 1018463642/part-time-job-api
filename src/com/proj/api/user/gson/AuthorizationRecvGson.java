package com.proj.api.user.gson;

import com.proj.api.exception.other.InvalidParamsException;

/**
 * Created by jangitlau on 2017/11/3.
 */
public class AuthorizationRecvGson {
    private String username;
    private String rand_str;
    private String pre_password;

    public String getUsername() throws InvalidParamsException {
        if (username == null) {
            throw new InvalidParamsException();
        } else {
            return username;
        }
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRand_str() throws InvalidParamsException {
        if (rand_str == null) {
            throw new InvalidParamsException();
        } else {
            return rand_str;
        }
    }

    public void setRand_str(String rand_str) {
        this.rand_str = rand_str;
    }

    public String getPre_password() throws InvalidParamsException {
        if (pre_password == null) {
            throw new InvalidParamsException();
        } else {
            return pre_password;
        }
    }

    public void setPre_password(String pre_password) {
        this.pre_password = pre_password;
    }
}
