package com.proj.api.exception.error;

/**
 * Created by jangitlau on 2017/11/2.
 */
public class Reason {
    public static String getReason(int _iErrCode){
        switch (_iErrCode){
            case 0:return "OK";
            default:return "未知错误";
        }
    }
}
