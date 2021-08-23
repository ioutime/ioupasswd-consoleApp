package com.ioutime.util;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

/**
 * @author ioutime
 * @version 1.0
 * @date 2021/8/21 22:52
 */

public class encryptUtil {
    public  String md5(String s) throws NoSuchAlgorithmException {
        MessageDigest md5 = MessageDigest.getInstance("md5");
        byte[] digest = md5.digest(s.getBytes(StandardCharsets.UTF_8));
        return new BigInteger(1, digest).toString(16);
    }

    public  String base64Decode(String s){
        byte[] decode = Base64.getDecoder().decode(s);
        return new String(decode,StandardCharsets.UTF_8);
    }
}
