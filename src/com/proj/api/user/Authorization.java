package com.proj.api.user;

import com.google.gson.Gson;
import com.proj.api.database.KeyValueDatabase;
import com.proj.api.exception.database.NonRelationalDatabaseException;
import com.proj.api.exception.user.InvaildOperationException;
import com.proj.api.exception.user.PasswordNotCorrectException;
import com.proj.api.user.gson.PreAuthorizationGson;
import com.proj.api.utils.RandomUtils;
import org.apache.commons.codec.digest.DigestUtils;

/**
 * Created by jangitlau on 2017/11/3.
 */
public class Authorization {
    private final static String sSalt = "";

    public Authorization(String _sUsername, String _sRandStr, String sPrePassword) throws NonRelationalDatabaseException, InvaildOperationException, PasswordNotCorrectException {
        KeyValueDatabase kvConn = new KeyValueDatabase();
        if (!kvConn.exists(PreAuthorizationGson.sessionPrefix + _sUsername)) {
            kvConn.close();
            throw new InvaildOperationException(); //未进行预授权，需要发出登录警告
        }
        Gson json = new Gson();
        PreAuthorizationGson preAuthorizationGson
                = json.fromJson(kvConn.get(PreAuthorizationGson.sessionPrefix + _sUsername), PreAuthorizationGson.class);
        if(!_sRandStr.equals(preAuthorizationGson.getiRandomKey())){
            kvConn.del(PreAuthorizationGson.sessionPrefix + _sUsername);
            kvConn.close();
            throw new PasswordNotCorrectException(); //随机数不正确，说明密码不正确，需要发出登录警告
        }
        String sAuthPassword= String.valueOf(DigestUtils.md5(sPrePassword+ Authorization.sSalt));
        if(!sAuthPassword.equals(preAuthorizationGson.getsAuthPassword())){
            kvConn.del(PreAuthorizationGson.sessionPrefix + _sUsername);
            kvConn.close();
            throw new PasswordNotCorrectException(); //验证密码不正确，基本上在随机数正确的情况下是不会发生的，需要发出登录警告
        }
        String sToken= RandomUtils.getRandomString(64);
        
    }
}
