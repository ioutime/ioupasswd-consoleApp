package com.ioutime.util;

import java.io.*;
import java.net.URL;
import java.util.Properties;

/**
 * @author ioutime
 * @version 1.0
 * @date 2021/8/21 9:24
 */

public class FileUtil {

    public boolean changeToken(String token){
        TokenUtil.token = token;
        return true;
    }

    public String getToken(){
        return TokenUtil.token;
    }

}
