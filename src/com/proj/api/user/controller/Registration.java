package com.proj.api.user.controller;

import com.google.gson.Gson;
import com.proj.api.database.KeyValueDatabase;
import com.proj.api.database.RelationalDatabase;
import com.proj.api.exception.database.NonRelationalDatabaseException;
import com.proj.api.exception.database.RelationalDatabaseException;
import com.proj.api.exception.user.InvaildOperationException;
import com.proj.api.exception.utils.AESDecryptException;
import com.proj.api.exception.utils.AESEncryptException;
import com.proj.api.user.gson.LoggedInUserInfGson;
import com.proj.api.user.gson.PreRegistrationInfGson;
import com.proj.api.utils.AESUtils;
import com.proj.api.utils.SaltUtils;
import org.apache.commons.codec.digest.DigestUtils;

/**
 * Created by jangitlau on 2017/11/3.
 */
public class Registration {
    private String sPreToken;
    private int iId;
    private int iType;

    public Registration(String _sUsername,String _sPasswordKey,int _iType) throws RelationalDatabaseException, NonRelationalDatabaseException, InvaildOperationException, AESDecryptException, AESEncryptException {
        if(_iType-2 > 0) {
            throw new InvaildOperationException();
        }
        this.iType = _iType;
        Gson json = new Gson();
        KeyValueDatabase kvConn = new KeyValueDatabase(PreRegistrationInfGson.sessionPrefix);
        if (!kvConn.exists(_sUsername)) {
            kvConn.close();
            throw new InvaildOperationException();
        }
        PreRegistrationInfGson preRegistrationInfGson = json.fromJson(kvConn.get(_sUsername), PreRegistrationInfGson.class);
        String sClearPassword = "";
        try {
            sClearPassword = AESUtils.decryptData(_sPasswordKey, preRegistrationInfGson.getRand_str());
        } catch (Exception e) {
            kvConn.close();
            throw new AESDecryptException();
        }
        String sTranPassword = DigestUtils.md5Hex(_sUsername + sClearPassword + SaltUtils.sTranPasswordSalt);
        String sAuthPassword = DigestUtils.md5Hex(
                DigestUtils.md5Hex(sClearPassword + SaltUtils.sPrePasswordSalt)
                        + SaltUtils.sAuthPasswordSalt);
        RelationalDatabase rConn = new RelationalDatabase();
        rConn.doSQL("INSERT INTO user_auth(username,tran_password,auth_password,type,authority,status) VALUES(?,?,?,?,?,?)"
                , new Object[]{_sUsername, sTranPassword, sAuthPassword, _iType, 1, 0});
        this.iId = rConn.getLastInsertId("user_auth");
        rConn.close();
        this.sPreToken= AESUtils.encryptData(preRegistrationInfGson.getRand_str(), sTranPassword);
        LoggedInUserInfGson loggedInUserInfGson=new LoggedInUserInfGson(this.iId,_sUsername,iType,1,0,preRegistrationInfGson.getRand_str(),System.currentTimeMillis());
        kvConn.setPrefix(LoggedInUserInfGson.sessionPrefix);
        kvConn.set(String.valueOf(iId), json.toJson(loggedInUserInfGson), LoggedInUserInfGson.iSessionExpire);
        kvConn.close();
    }

    public String getsPreToken() {
        return sPreToken;
    }

    public int getiId() {
        return iId;
    }

    public int getiType() {
        return iType;
    }
}
