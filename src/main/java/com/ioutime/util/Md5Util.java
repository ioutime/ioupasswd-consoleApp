package com.ioutime.util;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author ioutime
 * @version 1.0
 * @date 2021/8/21 22:52
 */

public class Md5Util {
    public static String md5(String s) throws NoSuchAlgorithmException {
        MessageDigest md5 = MessageDigest.getInstance("md5");
        byte[] digest = md5.digest(s.getBytes(StandardCharsets.UTF_8));
        return new BigInteger(1, digest).toString(16);
    }
}
