package com.example.utils.common;

import java.net.InetAddress;
import java.net.UnknownHostException;

import static com.example.utils.common.Print.println;

/**
 * @Description:
 * @author: liubao
 * @Date: Created in 2018/6/20 10:49
 */
public class OsUtils {
    /**
     * 获取机器名字
     * @return
     */
    public static String getHostAddress() {
        try {
            return InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
            return null;
        }
    }

    /**
     * 获取ip
     * 如果获取不到或者获取为127.0.0.1 那么修改机器的host文件中的配置
     * @return
     */
    public static String getHostName() {
        try {
            return InetAddress.getLocalHost().getHostName();
        } catch (UnknownHostException e) {
            return null;
        }
    }

    public static void main(String[] args) {
        println(OsUtils.getHostName());
        println(OsUtils.getHostAddress());
    }
}
