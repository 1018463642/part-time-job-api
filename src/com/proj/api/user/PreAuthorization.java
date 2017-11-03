package com.proj.api.user;

import com.google.gson.Gson;
import com.proj.api.database.KeyValueDatabase;
import com.proj.api.database.RelationalDatabase;
import com.proj.api.exception.database.NonRelationalDatabaseException;
import com.proj.api.exception.database.RelationalDatabaseException;
import com.proj.api.exception.user.UsernameNotExistException;
import com.proj.api.user.gson.PreAuthorizationGson;
import com.proj.api.utils.AESUtils;
import com.proj.api.utils.RandomUtils;
import redis.clients.jedis.exceptions.JedisException;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by jangitlau on 2017/11/2.
 */
public class PreAuthorization {
    private RelationalDatabase dbConn = null;
    private KeyValueDatabase jedisConn = null;

    private String sUsername;
    private String sKey;

    public PreAuthorization(String _sUsername) throws UsernameNotExistException, RelationalDatabaseException, NonRelationalDatabaseException {
        this.sUsername = _sUsername;
        String sRandomStr = RandomUtils.getRandomString(16);
        int iId, iType, iAuthority, iStatus;
        String sTranPassword, sAuthPassword;
        try {
            dbConn = new RelationalDatabase();
            ResultSet result = dbConn.doQuery("SELECT id,username,tran_password,auth_password,type,authority,status "
                    + " FROM user_auth WHERE username=?", new String[]{this.sUsername});
            if (result.first()) {
                iId = result.getInt("id");
                sTranPassword = result.getString("tran_password");
                sAuthPassword = result.getString("auth_password");
                iType = result.getInt("type");
                iAuthority = result.getInt("authority");
                iStatus = result.getInt("status");
            } else {
                throw new UsernameNotExistException();
            }
        } catch (SQLException e) {
            throw new RelationalDatabaseException(e);
        } finally {
            dbConn.close();
        }
        //construct session msg
        Gson json = new Gson();
        String session_msg = json.toJson(new PreAuthorizationGson(iId, this.sUsername, sTranPassword, sAuthPassword, sRandomStr, iType, iAuthority, iStatus));
        //load jedis and put session msg
        try {
            jedisConn = new KeyValueDatabase(PreAuthorizationGson.sessionPrefix);
            jedisConn.set(this.sUsername, session_msg, PreAuthorizationGson.iSessionExpire);
        } catch (JedisException e) {
            throw new NonRelationalDatabaseException(e);
        } finally {
            jedisConn.close();
        }
        this.sKey = AESUtils.encryptData(sRandomStr, sTranPassword).trim();
    }
}
