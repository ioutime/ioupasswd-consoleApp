package com.ioutime.util;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * @author ioutime
 * @version 1.0
 * @date 2021/8/21 9:24
 */

public class FileUtil {
    public Map<String, String> readFile() throws IOException {
        InputStream in = FileUtil.class.getClassLoader().getResourceAsStream("config.ini");
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        Properties props = new Properties();
        props.load(br);
        Map<String, String> map = new HashMap<>();
        for(Object s: props.keySet()){
            map.put(s.toString(), props.getProperty(s.toString()));
        }
        return map;
    }

    public boolean writeFile(String token)  {
        try{
            token = "token=" + token;
            FileOutputStream fileOutputStream = new FileOutputStream("src/main/resources/config.ini");
            byte[] bytes = token.getBytes(StandardCharsets.UTF_8);
            fileOutputStream.write(bytes);
            fileOutputStream.flush();
            fileOutputStream.close();
            return true;
        }catch (IOException e) {
           return false;
        }
    }

//    public static void main(String[] args) {
//        boolean b = new FileUtil().writeFile("123");
//        System.out.println(b);
//    }
}
