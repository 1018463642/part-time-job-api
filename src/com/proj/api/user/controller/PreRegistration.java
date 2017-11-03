package com.proj.api.user.controller;

import com.google.gson.Gson;
import com.proj.api.database.KeyValueDatabase;
import com.proj.api.database.RelationalDatabase;
import com.proj.api.exception.database.NonRelationalDatabaseException;
import com.proj.api.exception.database.RelationalDatabaseException;
import com.proj.api.exception.user.UserAlreadyExistException;
import com.proj.api.exception.utils.AESEncryptException;
import com.proj.api.user.gson.PreRegistrationInfGson;
import com.proj.api.utils.AESUtils;
import com.proj.api.utils.PhoneVerifyCodeUtils;
import com.proj.api.utils.RandomUtils;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by jangitlau on 2017/11/3.
 */
public class PreRegistration {
    private String sUsername;
    private String sKey;

    public PreRegistration(String _sUsername, String _sPhoneNum) throws NonRelationalDatabaseException, RelationalDatabaseException, UserAlreadyExistException, AESEncryptException {
        this.sUsername = _sUsername;
        RelationalDatabase rConn = new RelationalDatabase();
        ResultSet result = rConn.doQuery("SELECT id FROM user_auth WHERE username=?", new String[]{_sUsername});
        try {
            if (result.first()) {
                throw new UserAlreadyExistException();
            }
        } catch (SQLException e) {
            throw new RelationalDatabaseException(e);
        }
        String sRandomStr = RandomUtils.getRandomString(16);
        PreRegistrationInfGson preRegistrationGson = new PreRegistrationInfGson(_sUsername, _sPhoneNum, sRandomStr);
        Gson json = new Gson();
        KeyValueDatabase kvConn = new KeyValueDatabase(PreRegistrationInfGson.sessionPrefix);
        kvConn.set(_sUsername, json.toJson(preRegistrationGson), PreRegistrationInfGson.iSessionExpire);
        kvConn.close();
        String sPhoneVerifyCode = PhoneVerifyCodeUtils.send(_sPhoneNum);
        this.sKey = AESUtils.encryptData(sRandomStr, sPhoneVerifyCode);
        System.out.println("code: " + AESUtils.encryptData("12345678", sRandomStr));
    }

    public String getsUsername() {
        return sUsername;
    }

    public String getsKey() {
        return sKey;
    }
}
