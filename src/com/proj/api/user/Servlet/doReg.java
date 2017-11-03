package com.proj.api.user.Servlet;

import com.google.gson.Gson;
import com.proj.api.exception.database.NonRelationalDatabaseException;
import com.proj.api.exception.database.RelationalDatabaseException;
import com.proj.api.exception.error.ErrGsonStructure;
import com.proj.api.exception.other.InvalidParamsException;
import com.proj.api.exception.user.InvaildOperationException;
import com.proj.api.exception.user.PasswordNotCorrectException;
import com.proj.api.exception.user.UserAlreadyExistException;
import com.proj.api.exception.utils.AESDecryptException;
import com.proj.api.exception.utils.AESEncryptException;
import com.proj.api.user.controller.Authorization;
import com.proj.api.user.controller.PreRegistration;
import com.proj.api.user.controller.Registration;
import com.proj.api.user.gson.*;
import com.proj.api.utils.InputStrUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by jangitlau on 2017/11/3.
 */
public class doReg extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Gson json = new Gson();
        String retStr = "";
        try {
            String recvStr = InputStrUtils.getRecvString(request);
            RegistrationRecvGson registrationRecvGson = json.fromJson(recvStr, RegistrationRecvGson.class);
            Registration registration = new Registration(
                    registrationRecvGson.getUsername()
                    , registrationRecvGson.getPassword_key()
                    , registrationRecvGson.getType());
            RegistrationRetGson registrationRetGson = new RegistrationRetGson(
                    registration.getsPreToken()
                    , registration.getiId()
                    , registration.getiType());
            retStr = json.toJson(registrationRetGson);
        } catch (InvalidParamsException e) {
            retStr = json.toJson(new ErrGsonStructure(401));
        } catch (AESDecryptException e) {
            retStr = json.toJson(new ErrGsonStructure(404));
            e.printStackTrace();
        } catch (AESEncryptException e) {
            retStr = json.toJson(new ErrGsonStructure(405));
            e.printStackTrace();
        } catch (NonRelationalDatabaseException e) {
            retStr = json.toJson(new ErrGsonStructure(501));
        } catch (RelationalDatabaseException e) {
            retStr = json.toJson(new ErrGsonStructure(502));
            e.printStackTrace();
        } catch (InvaildOperationException e) {
            retStr = json.toJson(new ErrGsonStructure(503));
        }
        response.setHeader("content-type", "text/html;charset=utf-8");
        response.getWriter().print(retStr);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String retStr = "";
        Gson json = new Gson();
        try {
            String sUsername = request.getParameter("username");
            String sPhoneNum = request.getParameter("phone_num");
            if (sUsername == null || sPhoneNum == null) {
                throw new InvalidParamsException();
            }
            PreRegistration preRegistration = new PreRegistration(sUsername, sPhoneNum);
            PreRegistrationRetGson preRegistrationRetGson = new PreRegistrationRetGson(
                    preRegistration.getsUsername(), preRegistration.getsKey());
            retStr = json.toJson(preRegistration);
        } catch (InvalidParamsException e) {
            retStr = json.toJson(new ErrGsonStructure(401));
        } catch (UserAlreadyExistException e) {
            retStr = json.toJson(new ErrGsonStructure(403));
        } catch (AESEncryptException e) {
            retStr = json.toJson(new ErrGsonStructure(405));
        } catch (NonRelationalDatabaseException e) {
            retStr = json.toJson(new ErrGsonStructure(501));
        } catch (RelationalDatabaseException e) {
            retStr = json.toJson(new ErrGsonStructure(502));
        }
        response.setHeader("content-type", "text/html;charset=utf-8");
        response.getWriter().print(retStr);
    }
}
