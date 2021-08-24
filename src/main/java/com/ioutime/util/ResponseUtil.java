package com.ioutime.util;

import com.alibaba.fastjson.JSONObject;

import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.util.Objects;

/**
 * @author ioutime
 * @version 1.0
 * @date 2021/8/21 13:57
 */

public class ResponseUtil {
    private static final String IOU = "---->";

    /*处理login、register*/
    public boolean user(JSONObject jsonObject,boolean isLogin){
        if(!Objects.equals(null,jsonObject.get("code"))){
            System.out.println("服务器问题"+jsonObject.get("code"));
            System.out.print(IOU);
            return false;
        }else {
            JSONObject res = JSONObject.parseObject(jsonObject.get("res").toString());
            String code = res.get("code").toString();
            if(code.equals("200")){
                if(isLogin){
                    //保存token
                   new VarUtil().changeToken(res.get("data").toString());
                }
                String msg = res.get("msg").toString();
                System.out.println(new encryptUtil().base64Decode(msg));
                return true;
            }else {
                String msg = res.get("msg").toString();
                System.out.println(new encryptUtil().base64Decode(msg));
                return false;
            }
        }
    }

    /*处理add,del*/
    public void dispose(JSONObject jsonObject){
        if(!Objects.equals(null,jsonObject.get("code"))){
            System.out.println("服务器问题"+jsonObject.get("code"));
        }else {
            JSONObject res = JSONObject.parseObject(jsonObject.get("res").toString());
            String code = res.get("code").toString();
            String msg = res.get("msg").toString();
            System.out.println(new encryptUtil().base64Decode(msg));
        }

    }

    /*处理select,all*/
    public void getMessage(JSONObject jsonObject,boolean carryMsg){
        if(!Objects.equals(null,jsonObject.get("code"))){
            System.out.println("服务器问题"+jsonObject.get("code"));
        }else {
            JSONObject res = JSONObject.parseObject(jsonObject.get("res").toString());
            String code = res.get("code").toString();
            if(code.equals("200")){
                JSONObject data = JSONObject.parseObject(res.get("data").toString());
                int nums = Integer.parseInt(String.valueOf(data.get("nums")));
                System.out.println("一共查询到"+nums+"条");
                if(nums==0){
                    System.out.print(IOU);
                    return;
                }
                if(!carryMsg){
                    System.out.print("是否显示(y|n),回车直接显示:");
                    String show = new ScannerUtil().readScanner();
                    if(show.length()==0 || Objects.equals(show,"y") || Objects.equals(show,"Y")){
                        for (int i = 1; i <= nums; i++) {
                            JSONObject message = JSONObject.parseObject(data.get(String.valueOf(i)).toString());
                            String id = message.get("id").toString();
                            String notes = new encryptUtil().base64Decode(message.get("notes").toString());
                            System.out.println("序  号:  "+id);
                            System.out.println("备  注:  "+notes);
                            System.out.println("·························\n");
                        }
                    }
                    System.out.print(IOU);
                    return;
                }
                System.out.print(IOU);
                System.out.print("输入密钥用于解密:");
                String key = new ScannerUtil().readPwd();
                if(key.length()==0){
                    System.out.println("不能为空");
                    System.out.print(IOU);
                    return;
                }
                for (int i = 1; i <= nums; i++) {
                    JSONObject message = JSONObject.parseObject(data.get(String.valueOf(i)).toString());
                    String id = message.get("id").toString();
                    String notes = new encryptUtil().base64Decode(message.get("notes").toString());
                    String msg = message.get("msg").toString();
                    try {
                        String decrypt = new AESUtil().decrypt(key, msg);
                        String[] split = decrypt.split(";`");
                        System.out.println("序  号:  "+id);
                        System.out.println("备  注:  "+notes);
                        String[] name = {"网  址:  ","用户名:  ","密  码:  "};
                        for (int j = 0; j < 3; j++) {
                            System.out.println(name[j]+split[j]);
                        }
                        System.out.println("·························\n");
                    } catch (GeneralSecurityException | UnsupportedEncodingException e) {
                        System.out.println("解密失败");
                        System.out.print(IOU);
                        return;
                    }
                }
            }else {
                String msg = res.get("msg").toString();
                System.out.println(new encryptUtil().base64Decode(msg));
            }
        }
        System.out.print(IOU);
    }

}
