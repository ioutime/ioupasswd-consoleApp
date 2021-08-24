package com.ioutime;

import com.ioutime.service.imp.ConnectServiceImp;
import com.ioutime.service.imp.MessageServiceImp;
import com.ioutime.service.imp.UserServiceImp;
import com.ioutime.util.ByeUtil;
import com.ioutime.util.HelpUtil;
import com.ioutime.util.PasswordUtil;
import com.ioutime.util.ScannerUtil;

import java.io.IOException;
import java.util.Objects;

public class App 
{
    private static boolean state = true;
    private static boolean limit = false;
    private static final String IOU = "---->";
    static {
        System.out.println("-----------------WELCOME IOUPASSWD-----------------");
        System.out.println(
                "1、采用AES加密密码\n" +
                "2、自己记一个密钥key(7~10)\n" +
                "3、加密和解密都是用的这一个key\n" +
                "4、key忘记了,数据库里的信息无法解密\n" +
                "5、输入help可以查看所有命令\n");
    }

    public static void main(String[] args) {
        /*连接服务器*/
        ConnectServiceImp connectServiceImp = new ConnectServiceImp();
        UserServiceImp userServiceImp = new UserServiceImp();
        MessageServiceImp messageServiceImp = new MessageServiceImp();
        ByeUtil byeUtil = new ByeUtil();
        ScannerUtil scannerUtil = new ScannerUtil();
        try {
            boolean connect = connectServiceImp.connect();
            if (!connect){
                System.out.println("连接失败");
                return;
            }
            System.out.println("连接成功");
        } catch (IOException e) {
            System.out.println("连接出错");
            return;
        }

        System.out.print("是否是新用户(Y或N),回车直接进入登录:");
        String s = scannerUtil.readScanner();
        /*register*/
        if(Objects.equals(s,"Y") || Objects.equals(s,"y")){
            int i = userServiceImp.loginCount(false);
            if(i == 0) {
                System.out.println("\n游客登录,使用help命令");
                limit = true;
                System.out.print(IOU);
            }
        }
        /*login*/
        int i = userServiceImp.loginCount(true);
        if(i == 0) {
            System.out.println("\n游客登录,使用help命令");
            limit = true;
        }
        System.out.print(IOU);
        /*add,del,select,msg*/
        while (state){
            String param = scannerUtil.readScanner();
            /*add*/
            if(Objects.equals(param,"add") || Objects.equals(param,"ADD")){
                if(limit){
                    System.out.println("未登录");
                    System.out.print(IOU);
                    continue;
                }
                messageServiceImp.add();
            }
            /*select*/
            else if(Objects.equals(param,"select") || Objects.equals(param,"SELECT")){
                if(limit){
                    System.out.println("未登录");
                    System.out.print(IOU);
                    continue;
                }
                messageServiceImp.select();
            }
            /*all*/
            else if(Objects.equals(param,"all")){
                if(limit){
                    System.out.println("未登录");
                    System.out.print(IOU);
                    continue;
                }
                messageServiceImp.all(true);
            }
            /*notes*/
            else if(Objects.equals(param,"notes")){
                if(limit){
                    System.out.println("未登录");
                    System.out.print(IOU);
                    continue;
                }
                messageServiceImp.all(false);
            }
            /*del*/
            else if(Objects.equals(param,"del")){
                if(limit){
                    System.out.println("未登录");
                    System.out.print(IOU);
                    continue;
                }
                messageServiceImp.del();
            }
            /*exit*/
            else if(Objects.equals(param,"exit")){
                state = byeUtil.exit();
            }
            /*login*/
            else if(Objects.equals(param,"login")){
                boolean login = userServiceImp.login();
                if(login) limit = false;
                System.out.print(IOU);
            }
            /*register*/
            else if(Objects.equals(param,"register")){
                userServiceImp.register();
                System.out.print(IOU);
            }
            /*help*/
            else if(Objects.equals(param,"help")){
                new HelpUtil().help();
            }
            /*logout*/
            else if(Objects.equals(param,"logout")){
                byeUtil.clear();
                limit = true;
                System.out.print(IOU);
            }
            /*su*/
            else if(Objects.equals(param,"su")){
                byeUtil.clear();
                limit = true;
                boolean login = userServiceImp.login();
                if(login) limit = false;
                System.out.print(IOU);
            }
            /*pwd*/
            else if(Objects.equals(param,"pwd")){
                String password = new PasswordUtil().randomPWD();
                System.out.println(password);
                System.out.print(IOU);
            }
            else {
                System.out.print(IOU);
            }
        }
    }
}