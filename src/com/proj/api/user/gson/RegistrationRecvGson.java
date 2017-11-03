package com.proj.api.user.gson;

import com.proj.api.exception.other.InvalidParamsException;

/**
 * Created by jangitlau on 2017/11/3.
 */
public class RegistrationRecvGson {
    private String username;
    private String password_key;
    private int type;

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

    public String getPassword_key() throws InvalidParamsException {
        if (password_key == null) {
            throw new InvalidParamsException();
        } else {
            return password_key;
        }
    }

    public void setPassword_key(String password_key) {
        this.password_key = password_key;
    }

    public int getType() throws InvalidParamsException {
        if (type == 0) {
            throw new InvalidParamsException();
        } else {
            return type;
        }
    }

    public void setType(int type) {
        this.type = type;
    }
}
