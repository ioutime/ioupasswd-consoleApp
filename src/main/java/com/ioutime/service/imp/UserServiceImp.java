package com.ioutime.service.imp;

import com.alibaba.fastjson.JSONObject;
import com.ioutime.RequestMethod.SendReq;
import com.ioutime.service.UserService;
import com.ioutime.util.ResponseUtil;
import com.ioutime.util.ScannerUtil;
import com.ioutime.util.VarUtil;
import com.ioutime.util.encryptUtil;
import java.security.NoSuchAlgorithmException;
import java.util.Objects;

/**
 * @author ioutime
 * @version 1.0
 * @date 2021/8/23 10:06
 */

public class UserServiceImp implements UserService {

    private static int count = 3;
    private static final String IOU = "---->";
    public int loginCount(boolean isLogin){
        while (count != 0){
            if(isLogin){
                boolean login = login();
                if(login) break;
                else count--;
            }else {
                boolean register = register();
                if(register) break;
                else count--;
            }
        }
        return count;
    }

    @Override
    public boolean login() {
        System.out.println("用户登录");
        System.out.print(IOU);
        System.out.print("用户名:");
        String username = new ScannerUtil().readScanner();
        System.out.print(IOU);
        System.out.print("密  码:");
        String password = new ScannerUtil().readScanner();
        if(username.length()==0 || password.length()==0){
            System.out.println("用户名或密码不能为空");
            return false;
        }
        /*登录请求*/
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("username",username);
        try {
            jsonObject.put("password", new encryptUtil().md5(password));
        } catch (NoSuchAlgorithmException e) {
            System.out.println("加密问题");
            return false;
        }
        try {
            JSONObject post = new SendReq().sendOther("POST", VarUtil.BASEURL+"login", jsonObject);
            return new ResponseUtil().user(post,true);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("登录失败");
            return false;
        }
    }

    @Override
    public boolean register() {
        System.out.println("用户注册");
        System.out.print(IOU+"用 户 名:");
        String username = new ScannerUtil().readScanner();
        System.out.print(IOU+"密    码:");
        String password = new ScannerUtil().readScanner();
        System.out.print(IOU+"确认密码:");
        String sure = new ScannerUtil().readScanner();
        if(username.length()==0 || password.length()==0 || sure.length()==0 || !Objects.equals(password,sure)){
            System.out.println("输入有问题");
            return false;
        }
        /*注册请求*/
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("username",username);
        try {
            jsonObject.put("password",new encryptUtil().md5(password));
        } catch (NoSuchAlgorithmException e) {
            System.out.println("加密问题");
            return false;
        }
        try {
            JSONObject post = new SendReq().sendOther("POST", VarUtil.BASEURL+"register", jsonObject);
            return new ResponseUtil().user(post,false);
        } catch (Exception e) {
            System.out.println("登录失败");
            return false;
        }
    }
}
