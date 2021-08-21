package com.ioutime.service.imp;

import com.alibaba.fastjson.JSONObject;
import com.ioutime.RequestMethod.SendReq;
import com.ioutime.service.MessageService;
import com.ioutime.util.AESUtil;
import com.ioutime.util.FileUtil;
import com.ioutime.util.ResponseUtil;
import com.ioutime.util.ScannerUtil;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Base64;

/**
 * @author ioutime
 * @version 1.0
 * @date 2021/8/21 13:19
 */

public class MessageServiceImp implements MessageService {
    private static final String IOU = "---->";
    private final String BASEURL = "http://localhost:8088/api/";

    @Override
    public void add() {
        System.out.print(IOU);
        System.out.print("备注:");
        String notes = ScannerUtil.readScanner();
        if(notes.length() == 0){
            System.out.println("此选项为必填");
            System.out.print(IOU);
            return;
        }
        System.out.print(IOU);
        System.out.print("网址:");
        String url = ScannerUtil.readScanner();
        System.out.print(IOU);
        System.out.print("用户名:");
        String username = ScannerUtil.readScanner();
        System.out.print(IOU);
        System.out.print("密码:");
        String password = ScannerUtil.readScanner();
        String msg = url +";"+ username +";" + password;
        if(msg.length()==2){
            System.out.println("输入的信息为空");
            System.out.print(IOU);
            return;
        }
        System.out.print(IOU);
        System.out.print("加密密钥(唯一的,不能更改):");
        String key = ScannerUtil.readScanner();
        JSONObject params = new JSONObject();
        try {
            String encrypt = new AESUtil().encrypt(key, msg);
            params.put("notes",notes);
            params.put("msg",encrypt);
        } catch (GeneralSecurityException e) {
            System.out.println("加密错误"+e.getClass());
        }
        try {
            params.put("token", new FileUtil().readFile());
        } catch (IOException e) {
            System.out.print(IOU);
            System.out.println("token"+e.getClass());
        }
        try {
            JSONObject put = new SendReq().sendOther("PUT", BASEURL + "add", params);
            new ResponseUtil().dispose(put);
            System.out.println("继续操作...");
            System.out.print(IOU);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void del() {
        System.out.print(IOU);
        System.out.print("要删的序号:");
        String ids = ScannerUtil.readScanner();
        if(ids.length() == 0){
            System.out.println(IOU+"不能为空");
        }
        int id = Integer.parseInt(ids);
        try {
            String token = new FileUtil().readFile();
            JSONObject params = new JSONObject();
            params.put("id",id);
            params.put("token",token);
            JSONObject delete = new SendReq().sendOther("DELETE", BASEURL + "del", params);
            new ResponseUtil().dispose(delete);
            System.out.println("继续操作...");
            System.out.print(IOU);
        } catch (IOException e) {
            System.out.println("失败1"+e.getClass());
        } catch (Exception e) {
            System.out.println("失败2"+e.getClass());
        }


    }

    @Override
    public void select() {
        System.out.print(IOU);
        System.out.print("要查询的备注:");
        String note = ScannerUtil.readScanner();
        JSONObject jsonObject = new JSONObject();
        try {
            String token = new FileUtil().readFile();
            jsonObject.put("token",token);
            jsonObject.put("notes",note);
            JSONObject post = new SendReq().sendOther("POST", BASEURL + "select", jsonObject);
            new ResponseUtil().getMessage(post);
        } catch (IOException e) {
            System.out.println("失败1"+e.getClass());
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("失败2"+e.getClass());
        }
    }

    @Override
    public void all() {
        JSONObject jsonObject = new JSONObject();
        try {
            String token = new FileUtil().readFile();
            jsonObject.put("token",token);
            JSONObject post = new SendReq().sendOther("POST", BASEURL + "all", jsonObject);
            new ResponseUtil().getMessage(post);
        } catch (IOException e) {
            System.out.println("失败1"+e.getClass());
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("失败2"+e.getClass());
        }
    }
}
