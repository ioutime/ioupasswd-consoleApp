package com.ioutime.util;

import java.util.Arrays;
import java.util.List;

/**
 * @author ioutime
 * @version 1.0
 * @date 2021/8/23 11:43
 */

public class PasswordUtil {
    private static String SPECIAL_CHAR = "!\\\"#$%&'()*+,-./:;<=>?@[\\\\]^_`{|}~";
    private static String[] SIMPLE_WORDS = { "admin", "szim", "epicrouter", "password", "grouter", "dare", "root",
            "guest", "user", "success", "pussy", "mustang", "fuckme", "jordan", "test", "hunter", "jennifer", "batman",
            "thomas", "soccer", "sexy", "killer", "george", "asshole", "fuckyou", "summer", "hello", "secret", "fucker",
            "enter", "cookie", "administrator",
            // 中国网民常用密码
            "xiaoming", "taobao", "iloveyou", "woaini", "982464",
            // 国外网民常用密码
            "monkey", "letmein", "trustno1", "dragon", "baseball", "master", "sunshine", "ashley", "bailey", "shadow",
            "superman", "football", "michael", "qazwsx" };
    /*检测密码强度*/
    public boolean checkStrength(String password){
        char[] chPass = password.toCharArray();
        int digit = 0;
        int letter = 0;
        int specialChar = 0;
        for (char pass : chPass) {
            if(Character.isLetter(pass)){
                letter=1;
            }else if(Character.isDigit(pass)){
                digit=1;
            }else if(SPECIAL_CHAR.indexOf(pass) != -1){
                specialChar=1;
            }
        }
        int strength = digit + letter + specialChar;
        List<String> simpleWords = Arrays.asList(SIMPLE_WORDS);
        if(simpleWords.contains(password.toLowerCase()) || password.length() < 7 ){
            strength = -1;
        }
        return strength >= 2;
    }



}
