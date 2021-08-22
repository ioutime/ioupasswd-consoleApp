package com.ioutime.service;

import com.alibaba.fastjson.JSONObject;
import com.ioutime.RequestMethod.SendReq;
import com.ioutime.util.encryptUtil;
import com.ioutime.util.ResponseUtil;
import com.ioutime.util.ScannerUtil;

import java.security.NoSuchAlgorithmException;
import java.util.Objects;

/**
 * @author ioutime
 * @version 1.0
 * @date 2021/8/21 21:02
 */

public class RegisterService {
    private static final String BASEURL = "http://localhost:8088/api/";
    private static int count = 3;
    private static final String IOU = "---->";

    public int registerCount(){
        while (count != 0){
            boolean register = new RegisterService().register();
            if(register) break;
            else count--;
        }
        return count;
    }

    public boolean register(){
        System.out.println("用户注册");
        System.out.print(IOU);
        System.out.print("用 户 名:");
        String username = ScannerUtil.readScanner();
        System.out.print(IOU);
        System.out.print("密    码:");
        String password = ScannerUtil.readScanner();
        System.out.print(IOU);
        System.out.print("确认密码:");
        String sure = ScannerUtil.readScanner();
        if(username.length()==0 || password.length()==0 || sure.length()==0 || !Objects.equals(password,sure)){
            System.out.println("输入有问题");
            return false;
        }
        /*注册请求*/
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("username",username);
        try {
            jsonObject.put("password", encryptUtil.md5(password));
        } catch (NoSuchAlgorithmException e) {
            System.out.println("加密问题");
            return false;
        }
        try {
            JSONObject post = new SendReq().sendOther("POST", BASEURL+"register", jsonObject);
            return new ResponseUtil().register(post);
        } catch (Exception e) {
            System.out.println("登录失败"+e.getClass());
            return false;
        }
    }
}
