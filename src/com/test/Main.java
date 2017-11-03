package com.test;

import com.proj.api.exception.database.NonRelationalDatabaseException;
import com.proj.api.exception.database.RelationalDatabaseException;
import com.proj.api.exception.user.InvaildOperationException;
import com.proj.api.exception.user.PasswordNotCorrectException;
import com.proj.api.exception.user.UsernameNotExistException;
import com.proj.api.exception.utils.AESEncryptException;
import com.proj.api.user.controller.Authorization;
import com.proj.api.user.controller.PreAuthorization;

/**
 * Created by jangitlau on 2017/11/3.
 */
public class Main {
    public static void main(String[] args){
        try {
            PreAuthorization bb = new PreAuthorization("test");
            new Authorization("test", bb.rand(), "098f6bcd4621d373cade4e832627b4f6");
        } catch (UsernameNotExistException e) {
            e.printStackTrace();
        } catch (RelationalDatabaseException e) {
            e.printStackTrace();
        } catch (NonRelationalDatabaseException e) {
            e.printStackTrace();
        } catch (InvaildOperationException e) {
            e.printStackTrace();
        } catch (PasswordNotCorrectException e) {
            e.printStackTrace();
        } catch (AESEncryptException e) {
            e.printStackTrace();
        }
        System.exit(0);
    }
}
