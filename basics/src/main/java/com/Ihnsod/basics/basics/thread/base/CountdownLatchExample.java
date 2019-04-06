package com.Ihnsod.basics.basics.thread.base;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author: Ihnsod
 * @create: 2018/12/04 10:48
 **/
public class CountdownLatchExample {

    /**
     * 用来控制一个线程等待多个线程。
     *
     * 维护了一个计数器 cnt，每次调用 countDown()
     * 方法会让计数器的值减 1，减到 0 的时候，那些因为调用 await() 方法而在等待的线程就会被唤醒。
     */

    public static void main(String[] args) throws InterruptedException {
        final int totalThread = 10;
        CountDownLatch countDownLatch = new CountDownLatch(totalThread);
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i < totalThread; i++) {
            executorService.execute(() -> {
                System.out.print("run..");
                countDownLatch.countDown();
            });
        }
        countDownLatch.await();  //await到 countDownLatch里面的值减为0
        System.out.println("end");
        executorService.shutdown();
    }
}
