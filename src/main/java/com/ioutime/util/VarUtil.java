package com.ioutime.util;

/**
 * @author ioutime
 * @version 1.0
 * @date 2021/8/21 9:24
 */

public class VarUtil {

    public static String BASEURL = "";

    private static String token = "";

    public boolean changeToken(String token){
        VarUtil.token = token;
        return true;
    }

    public String getToken(){
        return token;
    }

}
