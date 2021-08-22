package com.ioutime.util;

/**
 * @author ioutime
 * @version 1.0
 * @date 2021/8/21 22:04
 */

public class HelpUtil {
    public void help(){
        System.out.println(
            "login    ----- 登录\n" +
            "register ----- 注册\n" +
            "exit     ----- 退出\n" +
            "all      ----- 查看所有信息\n" +
            "select   ----- 搜索信息\n" +
            "del      ----- 删除信息\n" +
            "add      ----- 添加信息\n" +
            "su       ----- 切换用户\n" +
            "logout   ----- 登出\n" +
            "help     ----- 帮助"
        );
        System.out.print("---->");
    }
}
