package com.proj.api.user;

import com.proj.api.database.KeyValueDatabase;
import com.proj.api.exception.database.NonRelationalDatabaseException;
import com.proj.api.exception.user.InvaildOperationException;
import com.proj.api.user.gson.PreAuthorizationGson;

/**
 * Created by jangitlau on 2017/11/3.
 */
public class Authorization {

    public Authorization(String _sUsername, String _sRandStr, String sPrePassword) throws NonRelationalDatabaseException, InvaildOperationException {
        KeyValueDatabase kvConn=new KeyValueDatabase();
        if(!kvConn.exists(PreAuthorizationGson.sessionPrefix+_sUsername)){
            throw new InvaildOperationException(); //未进行预授权
        }


    }
}
