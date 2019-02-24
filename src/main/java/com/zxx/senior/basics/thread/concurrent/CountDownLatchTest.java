package com.zxx.senior.basics.thread.concurrent;

import org.junit.Test;

import java.util.concurrent.CountDownLatch;

/**
 * @author: Elite
 * @date: 2018/6/17
 */
public class CountDownLatchTest {
    /**
     * CountDownLatch 作为计数器使用
     * 可以在多线程运行过程中使得某线程在某个线程执行过之后再执行的情况
     */
    @Test
    public void countDownLatch() {
        final CountDownLatch latch = new CountDownLatch(2);

        new Thread(() -> {
            try {
                System.err.println("子线程" + Thread.currentThread().getName() + "正在执行...");
                Thread.sleep(3000);
                System.err.println("子线程" + Thread.currentThread().getName() + "执行完毕...");
                latch.countDown();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        new Thread(() -> {
            try {
                System.err.println("子线程" + Thread.currentThread().getName() + "正在执行...");
                Thread.sleep(3000);
                System.err.println("子线程" + Thread.currentThread().getName() + "执行完毕...");
                latch.countDown();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        System.err.println("等待两个线程执行完毕...");
        try {
            latch.await();
            System.err.println("两个子线程已经执行完毕");
            System.err.println("继续执行主线程");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
