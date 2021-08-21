package com.ioutime.service;

import com.alibaba.fastjson.JSONObject;
import com.ioutime.RequestMethod.SendReq;
import com.ioutime.util.FileUtil;
import com.ioutime.util.ScannerUtil;

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
    private static boolean login(){
        System.out.println("请先登录");
        System.out.print(IOU);
        System.out.print("用户名:");
        String username = ScannerUtil.readScanner();
        System.out.print(IOU);
        System.out.print("密码:");
        String password = ScannerUtil.readScanner();
        /*登录请求*/
        FileUtil fileUtil = new FileUtil();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("username",username);
        jsonObject.put("password",password);
        try {
            JSONObject post = new SendReq().sendOther("POST", LOGIN, jsonObject);
            if(!Objects.equals(null,post.get("code"))){

                System.out.println("服务器问题"+post.get("code"));
                System.out.print(IOU);
                return false;
            }else {
                JSONObject res = JSONObject.parseObject(post.get("res").toString());
                String code = res.get("code").toString();
                if(code.equals("200")){
                    //保存token
                    boolean b = fileUtil.writeFile(res.get("data").toString());

                    System.out.println(res.get("msg").toString());
                    System.out.print(IOU);
                    return b;
                }else {

                    System.out.println(res.get("msg").toString());
                    System.out.print(IOU);
                    return false;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();

            System.out.println("登录失败"+e.getClass());
            System.out.print(IOU);
            return false;
        }
    }

}
