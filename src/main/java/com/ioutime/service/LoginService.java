package com.ioutime.service;

import com.alibaba.fastjson.JSONObject;
import com.ioutime.RequestMethod.SendReq;
import com.ioutime.util.encryptUtil;
import com.ioutime.util.ResponseUtil;
import com.ioutime.util.ScannerUtil;

import java.security.NoSuchAlgorithmException;

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
            jsonObject.put("password", encryptUtil.md5(password));
        } catch (NoSuchAlgorithmException e) {
            System.out.println("加密问题");
            return false;
        }
        try {
            JSONObject post = new SendReq().sendOther("POST", LOGIN, jsonObject);
            return new ResponseUtil().login(post);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("登录失败");
            return false;
        }
    }

}
