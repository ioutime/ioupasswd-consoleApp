package com.ioutime.util;

/**
 * @author ioutime
 * @version 1.0
 * @date 2021/8/21 11:37
 */

public class ByeUtil {
    public boolean exit(){
        boolean b = new FileUtil().writeFile("");
        System.out.println();
        System.out.println("-----------------BYE-----------------");
        return !b;
    }

    public void clear(){
        new FileUtil().writeFile("");
    }
}
