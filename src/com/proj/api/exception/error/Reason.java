package com.proj.api.exception.error;

/**
 * Created by jangitlau on 2017/11/2.
 */
public class Reason {
    public static String getReason(int _iErrCode){
        switch (_iErrCode){
            case 0:return "OK";

            case 100:return "";

            case 200:return "";

            case 300:return "";

            case 400:return "";

            case 500:return "";
            case 501:return "无效的参数";
            case 502:return "此用户名不存在";
            case 503:return "关系型数据库出现错误";
            case 504:return "非关系型数据库出现错误";

            default:return "未知错误";
        }
    }
}
