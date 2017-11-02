package com.proj.api.exception.database;

/**
 * Created by jangitlau on 2017/11/2.
 */
public class NonRelationalDatabaseException extends Exception {
    public Exception main_exception;
    public Exception sub_exception;

    public NonRelationalDatabaseException(Exception _oException) {
        this.main_exception = _oException;
    }

    public NonRelationalDatabaseException(Exception _oMain_exception,Exception _oSub_exception) {
        this.main_exception = _oMain_exception;
        this.sub_exception = _oSub_exception;
    }
}
