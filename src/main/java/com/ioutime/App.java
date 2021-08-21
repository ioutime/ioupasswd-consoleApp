package com.ioutime;


import com.ioutime.service.LoginService;
import com.ioutime.service.RegisterService;
import com.ioutime.service.imp.MessageServiceImp;
import com.ioutime.util.ByeUtil;
import com.ioutime.util.HelpUtil;
import com.ioutime.util.ScannerUtil;
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
                "2、自己记一个密钥key(6~10)\n" +
                "3、加密和解密都是用的这一个key\n" +
                "4、key忘记了,数据库里的信息无法解密\n" +
                "5、输入help可以查看所有命令\n");

    }

    public static void main(String[] args) {
        System.out.print("是否是新用户(Y或N),回车直接进入登录^-^-^-:");
        String s = ScannerUtil.readScanner();
        /*register*/
        if(Objects.equals(s,"Y") || Objects.equals(s,"y")){
            int i = new RegisterService().registerCount();
            if(i == 0) {
                System.out.println("\n游客登录,使用help命令");
                limit = true;
                System.out.print(IOU);
            }
        }
        /*login*/
        int i = new LoginService().loginCount();
        if(i == 0) {
            System.out.println("\n游客登录,使用help命令");
            limit = true;
        }
        System.out.print(IOU);
        /*add,del,select,msg*/
        while (state){
            String param = ScannerUtil.readScanner();
            /*add*/
            if(Objects.equals(param,"add") || Objects.equals(param,"ADD")){
                if(limit){
                    System.out.println("未登录");
                    System.out.print(IOU);
                    continue;
                }
                new MessageServiceImp().add();
            }
            /*select*/
            else if(Objects.equals(param,"select") || Objects.equals(param,"SELECT")){
                if(limit){
                    System.out.println("未登录");
                    System.out.print(IOU);
                    continue;
                }
                new MessageServiceImp().select();
            }
            /*all*/
            else if(Objects.equals(param,"all")){
                if(limit){
                    System.out.println("未登录");
                    System.out.print(IOU);
                    continue;
                }
                new MessageServiceImp().all();
            }
            /*del*/
            else if(Objects.equals(param,"del")){
                if(limit){
                    System.out.println("未登录");
                    System.out.print(IOU);
                    continue;
                }
                new MessageServiceImp().del();
            }
            /*exit*/
            else if(Objects.equals(param,"exit")){
                state = new ByeUtil().exit();
            }
            /*login*/
            else if(Objects.equals(param,"login")){
                boolean login = LoginService.login();
                if(login) limit = false;
                System.out.print(IOU);
            }
            /*register*/
            else if(Objects.equals(param,"register")){
                new RegisterService().register();
                System.out.print(IOU);
            }
            /*help*/
            else if(Objects.equals(param,"help")){
                new HelpUtil().help();
            }
            /*logout*/
            else if(Objects.equals(param,"logout")){
                new ByeUtil().clear();
                limit = true;
                System.out.print(IOU);
            }
            /*su*/
            else if(Objects.equals(param,"su")){
                new ByeUtil().clear();
                limit = true;
                boolean login = LoginService.login();
                if(login) limit = false;
                System.out.print(IOU);
            }
            else {
                System.out.print(IOU);
            }
        }
    }
}