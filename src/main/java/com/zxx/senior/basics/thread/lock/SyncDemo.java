package com.zxx.senior.basics.thread.lock;

import org.junit.Test;

/**
 * @author: Aries
 * @create: 2018/09/13 13:28
 **/
public class SyncDemo {
    @Test
    public void sync() {
        synchronized (SyncDemo.class) {
            System.err.println();
        }
    }
}
