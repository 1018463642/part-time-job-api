package com.proj.api.exception.error;

/**
 * Created by jangitlau on 2017/11/2.
 */
public class ErrGsonStructure {
    public String reason;

    public int err_code;

    public ErrGsonStructure(int err_code) {
        this.err_code = err_code;
        this.reason=Reason.getReason(err_code);
    }
}
