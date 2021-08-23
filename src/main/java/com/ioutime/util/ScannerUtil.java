package com.ioutime.util;

import java.util.Scanner;

/**
 * @author ioutime
 * @version 1.0
 * @date 2021/8/21 10:25
 */

public class ScannerUtil {
    public  String readScanner() {
        Scanner scan = new Scanner(System.in);
        String str2 = "";
        if (scan.hasNextLine()) {
            str2 = scan.nextLine();
        }
        return str2;
    }
}
