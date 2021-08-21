package com.ioutime.util;

/**
 * @author ioutime
 * @version 1.0
 * @date 2021/8/21 12:00
 */

public class SystemOutUtil {
    private static final String IOU = "IOUPASSWD->";
    public static void print(Object obj){
        System.out.println(obj);
        System.out.print(IOU);
    }
}
