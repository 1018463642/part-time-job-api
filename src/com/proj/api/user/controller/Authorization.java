package com.proj.api.user.controller;

import com.google.gson.Gson;
import com.proj.api.database.KeyValueDatabase;
import com.proj.api.exception.database.NonRelationalDatabaseException;
import com.proj.api.exception.user.InvaildOperationException;
import com.proj.api.exception.user.PasswordNotCorrectException;
import com.proj.api.user.gson.LoggedInUserInfGson;
import com.proj.api.user.gson.PreAuthorizationGson;
import com.proj.api.utils.AESUtils;
import com.proj.api.utils.RandomUtils;
import org.apache.commons.codec.digest.DigestUtils;

/**
 * Created by jangitlau on 2017/11/3.
 */
public class Authorization {
    private final static String sAuthPasswordSalt = "";
    private int iId;
    private String sUsername;
    private String sPreToken;

    public Authorization(String _sUsername, String _sRandStr, String sPrePassword) throws NonRelationalDatabaseException, InvaildOperationException, PasswordNotCorrectException {
        KeyValueDatabase kvConn = new KeyValueDatabase(PreAuthorizationGson.sessionPrefix);
        if (!kvConn.exists(_sUsername)) {
            kvConn.close();
            throw new InvaildOperationException(); //未进行预授权，需要发出登录警告
        }
        Gson json = new Gson();
        PreAuthorizationGson preAuthorizationGson
                = json.fromJson(kvConn.get(_sUsername), PreAuthorizationGson.class);
        if (!_sRandStr.equals(preAuthorizationGson.getiRandomKey())) {
            kvConn.del(_sUsername);
            kvConn.close();
            throw new PasswordNotCorrectException(); //随机数不正确，说明密码不正确，需要发出登录警告
        }
        String sAuthPassword = String.valueOf(DigestUtils.md5(sPrePassword + Authorization.sAuthPasswordSalt));
        if (!sAuthPassword.equals(preAuthorizationGson.getsAuthPassword())) {
            kvConn.del(_sUsername);
            kvConn.close();
            throw new PasswordNotCorrectException(); //验证密码不正确，基本上在随机数正确的情况下是不会发生的，需要发出登录警告
        }
        kvConn.del(_sUsername);
        String sToken = RandomUtils.getRandomString(64);
        LoggedInUserInfGson loggedInUserInfGson = new LoggedInUserInfGson(
                preAuthorizationGson.getiId()
                , preAuthorizationGson.getsUserName()
                , preAuthorizationGson.getiType()
                , preAuthorizationGson.getiAuthority()
                , preAuthorizationGson.getiStatus()
                , sToken, System.currentTimeMillis());
        int iId = preAuthorizationGson.getiId();
        kvConn.setPrefix(LoggedInUserInfGson.sessionPrefix);
        kvConn.set(String.valueOf(iId), json.toJson(loggedInUserInfGson), LoggedInUserInfGson.iSessionExpire);
        kvConn.close();
        this.iId=preAuthorizationGson.getiId();
        this.sUsername=preAuthorizationGson.getsUserName();
        this.sPreToken= AESUtils.encryptData(sToken, preAuthorizationGson.getsTranPassword());
        System.out.println(json.toJson(loggedInUserInfGson));
    }

    public int getiId() {
        return iId;
    }

    public String getsUsername() {
        return sUsername;
    }

    public String getsPreToken() {
        return sPreToken;
    }
}
