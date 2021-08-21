package com.ioutime;


import com.ioutime.service.LoginService;
import com.ioutime.service.imp.MessageServiceImp;
import com.ioutime.util.ByeUtil;
import com.ioutime.util.ScannerUtil;
import java.io.IOException;
import java.util.Objects;

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
            String param = ScannerUtil.readScanner();
            if(Objects.equals(param,"add") || Objects.equals(param,"ADD")){
                new MessageServiceImp().add();
            }else if(Objects.equals(param,"select") || Objects.equals(param,"SELECT")){
                new MessageServiceImp().select();
            }else if(Objects.equals(param,"all")){
                new MessageServiceImp().all();
            }else if(Objects.equals(param,"del")){
                new MessageServiceImp().del();
            }else if(Objects.equals(param,"exit")){
                new ByeUtil();
            }else {
                System.out.print(IOU);
            }

        }


    }
}