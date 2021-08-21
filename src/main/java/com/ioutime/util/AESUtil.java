package com.ioutime.util;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.GeneralSecurityException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

/**
 * @author ioutime
 * @version 1.0
 * @date 2021/8/20 20:54
 */

public class AESUtil {

    private  final String  AES = "AES/CBC/PKCS5Padding";

    // 加密,返回的时Base64编码过的:
    public  String encrypt(String key32, String message) throws GeneralSecurityException {
        byte[] key = padding(key32);
        byte[] input = message.getBytes(StandardCharsets.UTF_8);
        Cipher cipher = Cipher.getInstance(AES);
        SecretKeySpec keySpec = new SecretKeySpec(key, "AES");
        // CBC模式需要生成一个16 bytes的initialization vector:
        SecureRandom sr = SecureRandom.getInstanceStrong();
        byte[] iv = sr.generateSeed(16);
        IvParameterSpec ivps = new IvParameterSpec(iv);
        cipher.init(Cipher.ENCRYPT_MODE, keySpec, ivps);
        byte[] data = cipher.doFinal(input);
        // IV不需要保密，把IV和密文一起返回 Base64:
        String encode = Base64.getEncoder().encodeToString(join(iv, data));
        return encode;
    }

    // 解密:
    public  String decrypt(String key32,  String ciphertext ) throws GeneralSecurityException, UnsupportedEncodingException {
        byte[] key = padding(key32);
        byte[] input = Base64.getDecoder().decode(ciphertext);
        // 把input分割成IV和密文:
        byte[] iv = new byte[16];
        byte[] data = new byte[input.length - 16];
        System.arraycopy(input, 0, iv, 0, 16);
        System.arraycopy(input, 16, data, 0, data.length);
        // 解密:
        Cipher cipher = Cipher.getInstance(AES);
        SecretKeySpec keySpec = new SecretKeySpec(key, "AES");
        IvParameterSpec ivps = new IvParameterSpec(iv);
        cipher.init(Cipher.DECRYPT_MODE, keySpec, ivps);
        String s = new String(cipher.doFinal(data), "UTF-8");
        return s;
    }

    public static byte[] join(byte[] bs1, byte[] bs2) {
        byte[] r = new byte[bs1.length + bs2.length];
        System.arraycopy(bs1, 0, r, 0, bs1.length);
        System.arraycopy(bs2, 0, r, bs1.length, bs2.length);
        return r;
    }

    public static byte[] padding(String key) throws NoSuchAlgorithmException {
        int len = key.length();
        if(len == 32){
            return key.getBytes(StandardCharsets.UTF_8);
        }else{
            MessageDigest md5 = MessageDigest.getInstance("md5");
            byte[] digest = md5.digest(key.getBytes(StandardCharsets.UTF_8));
            return digest;
        }
    }
}
