package com.proj.api.utils;

import com.proj.api.exception.other.InvalidParamsException;

import java.io.BufferedReader;

/**
 * Created by jangitlau on 2017/11/3.
 */
public class InputStrUtils {
    public static String getRecvString(javax.servlet.http.HttpServletRequest request) throws InvalidParamsException {
        StringBuffer buffer = new StringBuffer();
        String line = null;
        try {
            BufferedReader reader = request.getReader();
            while((line = reader.readLine()) != null) {
                buffer.append(line);
            }
        } catch (Exception e) {
            throw new InvalidParamsException();
        }
        return buffer.toString();
    }
}
