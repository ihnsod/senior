package com.zxx.senior.basics.io.bio;

import java.io.IOException;
import java.net.ServerSocket;

/**
 * @author: Ihnsod
 * @create: 2019/01/14 13:21
 **/
public class BioDemo {

    public static void main(String[] args) {
        try {
            ServerSocket socket = new ServerSocket(8080);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
