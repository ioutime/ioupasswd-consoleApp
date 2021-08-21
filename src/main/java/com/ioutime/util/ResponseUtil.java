package com.ioutime.util;

import com.alibaba.fastjson.JSONObject;

import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.util.Arrays;
import java.util.Objects;

/**
 * @author ioutime
 * @version 1.0
 * @date 2021/8/21 13:57
 */

public class ResponseUtil {
    private static final String IOU = "---->";
    /*处理login*/
    public boolean login(JSONObject jsonObject){
        if(!Objects.equals(null,jsonObject.get("code"))){
            System.out.println("服务器问题"+jsonObject.get("code"));
            System.out.print(IOU);
            return false;
        }else {
            JSONObject res = JSONObject.parseObject(jsonObject.get("res").toString());
            String code = res.get("code").toString();
            if(code.equals("200")){
                //保存token
                boolean b = new FileUtil().writeFile(res.get("data").toString());
                System.out.println(res.get("msg").toString());
                return b;
            }else {
                System.out.println(res.get("msg").toString());
                return false;
            }
        }
    }

    public boolean register(JSONObject jsonObject){
        if(!Objects.equals(null,jsonObject.get("code"))){
            System.out.println("服务器问题"+jsonObject.get("code"));
            return false;
        }else {
            JSONObject res = JSONObject.parseObject(jsonObject.get("res").toString());
            String code = res.get("code").toString();
            if(Objects.equals(code,"200")){
                System.out.println(res.get("msg").toString());
                return true;
            }else{
                System.out.println(res.get("msg").toString());
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
            System.out.println(res.get("msg").toString());
        }

    }

    /*处理select,all*/
    public void getMessage(JSONObject jsonObject){
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
                System.out.print(IOU);
                System.out.print("输入密钥用于解密:");
                String key = ScannerUtil.readScanner();
                if(key.length()==0){
                    System.out.println("不能为空");
                    System.out.print(IOU);
                    return;
                }
                for (int i = 1; i <= nums; i++) {
                    JSONObject message = JSONObject.parseObject(data.get(String.valueOf(i)).toString());
                    String id = message.get("id").toString();
                    String notes =  message.get("notes").toString();
                    String msg = message.get("msg").toString();
                    try {
                        String decrypt = new AESUtil().decrypt(key, msg);
                        String[] split = decrypt.split(";`");
                        System.out.println("序  号:"+id);
                        System.out.println("备  注:"+notes);
                        String[] name = {"网  址:","用户名:","密  码:"};
                        for (int j = 0; j < 3; j++) {
                            System.out.println(name[j]+split[j]);
                        }
                        System.out.println("********************");
                    } catch (GeneralSecurityException | UnsupportedEncodingException e) {
                        System.out.println("解密失败"+e.getClass());
                        System.out.print(IOU);
                        return;
                    }
                }
            }else {
                System.out.println(res.get("msg").toString());
            }
        }
        System.out.print(IOU);
    }

}
