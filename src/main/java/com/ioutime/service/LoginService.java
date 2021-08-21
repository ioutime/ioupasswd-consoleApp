package com.ioutime.service;

import com.alibaba.fastjson.JSONObject;
import com.ioutime.RequestMethod.SendReq;
import com.ioutime.util.*;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.GeneralSecurityException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Locale;
import java.util.Objects;

/**
 * @author ioutime
 * @version 1.0
 * @date 2021/8/21 11:06
 */

public class LoginService {

    private static final String LOGIN = "http://localhost:8088/api/login";
    private static int count = 3;
    private static final String IOU = "---->";
    public int loginCount(){
        while (count != 0){
            boolean login = login();
            if(login) break;
            else count--;
        }
        return count;
    }
    public static boolean login(){
        System.out.println("用户登录");
        System.out.print(IOU);
        System.out.print("用户名:");
        String username = ScannerUtil.readScanner();
        System.out.print(IOU);
        System.out.print("密  码:");
        String password = ScannerUtil.readScanner();
        if(username.length()==0 || password.length()==0){
            System.out.println("用户名或密码不能为空");
            return false;
        }
        /*登录请求*/
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("username",username);
        try {
            jsonObject.put("password", Md5Util.md5(password));
        } catch (NoSuchAlgorithmException e) {
            System.out.println("加密问题");
            return false;
        }
        try {
            JSONObject post = new SendReq().sendOther("POST", LOGIN, jsonObject);
            boolean login = new ResponseUtil().login(post);
            return login;
        } catch (Exception e) {
            System.out.println("登录失败"+e.getClass());
            return false;
        }
    }
    private static String md5Util(String s) throws NoSuchAlgorithmException {
        MessageDigest md5 = MessageDigest.getInstance("md5");
        byte[] digest = md5.digest(s.getBytes(StandardCharsets.UTF_8));
        return new BigInteger(1, digest).toString(16);
    }

}
