package com.ioutime.service.imp;

import com.alibaba.fastjson.JSONObject;
import com.ioutime.RequestMethod.SendReq;
import com.ioutime.service.MessageService;
import com.ioutime.util.AESUtil;
import com.ioutime.util.VarUtil;
import com.ioutime.util.ResponseUtil;
import com.ioutime.util.ScannerUtil;

import java.security.GeneralSecurityException;

/**
 * @author ioutime
 * @version 1.0
 * @date 2021/8/21 13:19
 */

public class MessageServiceImp implements MessageService {
    private static final String IOU = "---->";

    @Override
    public void add() {
        System.out.print(IOU+"备注:");
        String notes = new ScannerUtil().readScanner();
        if(notes.length() == 0){
            System.out.println("此选项为必填");
            System.out.print(IOU);
            return;
        }
        System.out.print(IOU+"网址:");
        String url = new ScannerUtil().readScanner();
        System.out.print(IOU+"用户名:");
        String username = new ScannerUtil().readScanner();
        System.out.print(IOU+"密码:");
        String password = new ScannerUtil().readScanner();
        String msg = url +";`"+ username +";`" + password;
        if(msg.length()==4){
            System.out.println("输入的信息为空");
            System.out.print(IOU);
            return;
        }
        System.out.print(IOU+"加密密钥(唯一的,不能更改):");
        String key = new ScannerUtil().readScanner();
        JSONObject params = new JSONObject();
        try {
            String encrypt = new AESUtil().encrypt(key, msg);
            params.put("notes",notes);
            params.put("msg",encrypt);
        } catch (GeneralSecurityException e) {
            System.out.println("加密错误");
        }
        params.put("token", new VarUtil().getToken());

        try {
            JSONObject put = new SendReq().sendOther("PUT", VarUtil.BASEURL + "add", params);
            new ResponseUtil().dispose(put);
            System.out.println("继续操作...");
            System.out.print(IOU);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void del() {
        System.out.print(IOU+"要删的序号:");
        String ids = new ScannerUtil().readScanner();
        if(ids.length() == 0){
            System.out.println("不能为空");
            System.out.print(IOU);
            return;
        }
        int id;
        try{
            id = Integer.parseInt(ids);
        }catch (NumberFormatException e){
            System.out.println("数据格式不符合");
            System.out.print(IOU);
            return;
        }
        try {
            String token = new VarUtil().getToken();
            JSONObject params = new JSONObject();
            params.put("id",id);
            params.put("token",token);
            JSONObject delete = new SendReq().sendOther("DELETE", VarUtil.BASEURL + "del", params);
            new ResponseUtil().dispose(delete);
            System.out.println("继续操作...");
            System.out.print(IOU);
        } catch (Exception e) {
            System.out.println("失败del");
        }


    }

    @Override
    public void select() {
        System.out.print(IOU);
        System.out.print("要查询的备注:");
        String note = new ScannerUtil().readScanner();
        JSONObject jsonObject = new JSONObject();
        try {
            String token = new VarUtil().getToken();
            jsonObject.put("token",token);
            jsonObject.put("notes",note);
            JSONObject post = new SendReq().sendOther("POST", VarUtil.BASEURL + "select", jsonObject);
            new ResponseUtil().getMessage(post,true);
        } catch (Exception e) {
            System.out.println("失败select");
        }
    }

    @Override
    public void all(boolean carryMsg) {
        JSONObject jsonObject = new JSONObject();
        try {
            String token = new VarUtil().getToken();
            jsonObject.put("token",token);
            JSONObject post = new SendReq().sendOther("POST", VarUtil.BASEURL + "all", jsonObject);
            new ResponseUtil().getMessage(post,carryMsg);
        } catch (Exception e) {
            System.out.println("失败all");
        }
    }
}
