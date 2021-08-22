package com.ioutime.util;

import java.io.*;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * @author ioutime
 * @version 1.0
 * @date 2021/8/21 9:24
 */

public class FileUtil {
    public String readFile() throws IOException {
        InputStream in = FileUtil.class.getClassLoader().getResourceAsStream("config.ini");
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        Properties props = new Properties();
        props.load(br);
        String token = props.getProperty("token");
        return token;
    }

    public boolean writeFile(String token)  {
        URL file = FileUtil.class.getClassLoader().getResource("");
        String path = file.getFile() + "config.ini";
        try {
            FileOutputStream outputStream = new FileOutputStream(path);
            Properties properties = new Properties();
            properties.setProperty("token",token);
            properties.store(outputStream,"update");
            return true;
        } catch (IOException e) {
            return false;
        }
    }

}
