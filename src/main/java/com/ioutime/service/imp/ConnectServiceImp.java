package com.ioutime.service.imp;

import com.alibaba.fastjson.JSONObject;
import com.ioutime.RequestMethod.SendReq;
import com.ioutime.service.ConnectService;
import com.ioutime.util.ScannerUtil;
import com.ioutime.util.VarUtil;

import java.io.*;
import java.util.Objects;
import java.util.Properties;

/**
 * @author ioutime
 * @version 1.0
 * @date 2021/8/23 13:34
 */

public class ConnectServiceImp implements ConnectService {

    private static final File file = new File("config.ini");
    private static final String IOU = "---->";

    @Override
    public boolean connect() throws IOException {
        if(file.exists()){
            FileInputStream inputStream = new FileInputStream(file);
            Properties properties = new Properties();
            properties.load(inputStream);
            String host = properties.getProperty("host");
            String port = properties.getProperty("port");
            inputStream.close();
            if(Objects.equals(null,host) || Objects.equals(null,port) || host.length()==0 || port.length()==0){
                return write();
            }else {
                if(!isPort(port)) return false;
                VarUtil.BASEURL = "http://"+host+":"+port+"/api/";
                if(testCon()){
                    return true;
                }else {
                    return write();
                }
            }
        }else {
            if(file.createNewFile()){
                return write();
            }else {
                System.out.println("创建文件失败");
                return false;
            }

        }
    }

    private static boolean write() throws IOException {
        int count = 3;
        while (count !=0){
            System.out.println("-连接服务器");
            ScannerUtil scannerUtil = new ScannerUtil();
            System.out.print(IOU+"主机: ");
            String host = scannerUtil.readScanner();
            System.out.print(IOU+"端口: ");
            String port = scannerUtil.readScanner();
            if(Objects.equals(null,host) || Objects.equals(null,port)){
                System.out.println("主机或端口不能为空");
                count--;
                continue;
            }
            if(!isPort(port)) return false;
            VarUtil.BASEURL = "http://"+host+":"+port+"/api/";
            if(testCon()){
                FileOutputStream outputStream = new FileOutputStream(file);
                Properties properties = new Properties();
                properties.setProperty("host",host);
                properties.setProperty("port",port);
                properties.store(outputStream,"update");
                outputStream.close();
                return true;
            }else {
                count--;
            }
        }
        return false;
    }

    private static boolean testCon(){
        try {
            JSONObject jsonObject = new SendReq().sendGet(VarUtil.BASEURL+"hello");
            return Objects.equals(null,jsonObject.get("code"));
        } catch (IOException e) {
            System.out.println("配置不对\n");
            return false;
        }
    }

    private static boolean isPort(String port){
        try {
            int i = Integer.parseInt(port);
            if(i > 65535 || i < 0){
                System.out.println("端口范围");
                return false;
            }
            return true;
        }catch (NumberFormatException e){
            System.out.println("数据格式不对");
            return false;
        }
    }
}
