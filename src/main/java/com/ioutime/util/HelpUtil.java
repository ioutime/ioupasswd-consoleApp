package com.ioutime.util;

/**
 * @author ioutime
 * @version 1.0
 * @date 2021/8/21 22:04
 */

public class HelpUtil {
    public void help(){
        System.out.println(
            "login    -----  登   录\n" +
            "register -----  注   册\n" +
            "su       -----  切换用户\n" +
            "logout   -----  登   出\n" +
            "all      -----  查看所有信息\n" +
            "select   -----  搜索信息\n" +
            "notes    -----  查看所有备注\n" +
            "del      -----  删除信息\n" +
            "add      -----  添加信息\n" +
            "exit     -----  退   出\n" +
            "pwd      -----  随机生成密码\n" +
            "help     -----  帮   助"
        );
        System.out.print("---->");
    }
}
