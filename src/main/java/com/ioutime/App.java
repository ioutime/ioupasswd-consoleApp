package com.ioutime;



import com.alibaba.fastjson.JSONObject;
import com.ioutime.RequestMethod.SendReq;
import com.ioutime.service.LoginService;
import com.ioutime.util.ByeUtil;
import com.ioutime.util.FileUtil;
import com.ioutime.util.ScannerUtil;

import javax.naming.NamingEnumeration;
import java.io.IOException;
import java.util.Map;
import java.util.Objects;
import java.util.Scanner;

public class App 
{
    private static boolean state = true;
    private static final String IOU = "---->";


    static {
        System.out.println("-----------------WELCOME IOUPASSWD-----------------");
        System.out.print(IOU);
    }

    public static void main(String[] args) throws IOException {
        /*login*/
        int i = new LoginService().loginCount();
        if(i == 0) new ByeUtil();
        /*add,del,select,msg*/
        while (state){

        }


    }



}

//    public static void main(String[] args) throws Exception {
//        SendReq send = new SendReq();
//        String url = "http://localhost:8088/api/add";
//        JSONObject body = new JSONObject();
//        String msg = "你好";
//        AESUtil util = new AESUtil();
//        String encrypt = null;
//        try {
//            encrypt = util.encrypt("1234", msg);
//        } catch (GeneralSecurityException e) {
//            e.printStackTrace();
//        }
//        body.put("notes","网易云");
//        body.put("msg",encrypt);
//        body.put("token", "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ1aWQiOiIxIiwiZXhwIjoxNjI5NDc2ODg5LCJ1c2VybmFtZSI6ImlvdXRpbWUifQ.xUPRArthWNSZuNGctyHLlTXAVXnMyAbiaiXzNK-lm-k");
//        JSONObject jsonObject = null;
//        try {
//            jsonObject = send.sendOther("PUT",url, body);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        System.out.println(jsonObject.toString());
//        url = "http://localhost:8088/api/login";
//        body.clear();
//        body.put("username","ioutime");
//        body.put("password","123456");
//        JSONObject post = send.sendOther("POST", url, body);
//        System.out.println(post.toString());
//    }