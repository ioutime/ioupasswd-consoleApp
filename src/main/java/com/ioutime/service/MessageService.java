package com.ioutime.service;

/**
 * @author ioutime
 * @version 1.0
 * @date 2021/8/21 13:12
 */

public interface MessageService {

    void add();

    void del();

    void select();

    void all(boolean carryMsg);
}
