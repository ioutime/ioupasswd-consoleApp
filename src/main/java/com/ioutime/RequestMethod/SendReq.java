package com.ioutime.RequestMethod;

import com.alibaba.fastjson.JSONObject;
import com.ioutime.util.StreamUtil;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;


/**
 * @author ioutime
 * @version 1.0
 * @date 2021/8/20 18:02
 */

public class SendReq {

    private static final String USER_AGENT = "Mozilla/5.0";
    // HTTP GET请求
    public JSONObject sendGet(String url) throws Exception {
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        //默认值我GET
        con.setRequestMethod("GET");
        //添加请求头
        con.setRequestProperty("User-Agent", USER_AGENT);
        int responseCode = con.getResponseCode();
        return judge(responseCode, con);
    }

    // HTTP POST,PUT,DELETE请求
    public JSONObject sendOther(String method, String url, JSONObject param) throws Exception {
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        //添加请求头
        con.setRequestMethod(method);
        con.setRequestProperty("User-Agent", USER_AGENT);

        //发送Post请求
        con.setDoOutput(true);
        //添加body内容
        StreamUtil.writeRequest(con,param);

        int responseCode = con.getResponseCode();
        return judge(responseCode, con);
    }

    private static JSONObject judge(int responseCode,HttpURLConnection con) throws IOException {
        JSONObject jsonObject = new JSONObject();
        if(responseCode == 200){
            //获取响应
            String response = StreamUtil.readResponse(con);
            jsonObject = JSONObject.parseObject(response);

        }else {
            jsonObject.put("code",500);
        }
        return jsonObject;
    }


}
