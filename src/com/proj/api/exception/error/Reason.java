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

            case 400:return "外部参数错误";
            case 401:return "无效的参数"; //InvalidParamsException
            case 402:return "此用户名不存在";
            case 403:return "此用户名已经存在"; //UserAlreadyExistException
            case 404:return "密文使用AES算法解密失败"; //AESDecryptException
            case 405:return "用户名或密码错误"; //PasswordNotCorrectException

            case 500:return "系统内部错误";
            case 501:return "非关系型数据库出现错误"; //NonRelationalDatabaseException
            case 502:return "关系型数据库出现错误"; //RelationalDatabaseException
            case 503:return "非法的操作";

            default:return "未知错误";
        }
    }
}
