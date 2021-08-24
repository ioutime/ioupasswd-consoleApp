package com.ioutime.util;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

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

    /**
     * 生成8位随机密码
     */
    public  String randomPWD(){
        String special = "#$%&*+,-.@";
        int i = 1234567890;
        String s ="qwertyuiopasdfghjklzxcvbnm";
        String S=s.toUpperCase();
        String word=s+S+i;
        char[] c=word.toCharArray();
        char[] sp = special.toCharArray();
        Random rd = new Random();
        StringBuilder plaintext = new StringBuilder();
        for (int k = 0; k <= 7; k++) {
            int index = rd.nextInt(c.length);//随机获取数组长度作为索引
            plaintext.append(c[index]);//循环添加到字符串后面
        }
        plaintext.append(sp[rd.nextInt(sp.length)]);
        return plaintext.toString();
    }

}
