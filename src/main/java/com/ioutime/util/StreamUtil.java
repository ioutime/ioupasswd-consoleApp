package com.ioutime.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.io.*;
import java.net.HttpURLConnection;

/**
 * @author ioutime
 * @version 1.0
 * @date 2021/8/20 20:08
 */

public class StreamUtil {

    /*Read*/
    public static String readResponse(HttpURLConnection con) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();
        return response.toString();
    }

    /*Write*/
    public static void writeRequest(HttpURLConnection con, JSONObject param) throws IOException {
        OutputStreamWriter writer = new OutputStreamWriter(con.getOutputStream(),"UTF-8");
        writer.write(param.toString());
        writer.flush();
        writer.close();
    }

}
