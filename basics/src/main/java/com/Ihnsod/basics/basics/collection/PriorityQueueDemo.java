package com.Ihnsod.basics.basics.collection;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @author: Ihnsod
 * @create: 2019/01/13 00:31
 **/
public class PriorityQueueDemo {

    public static void main(String[] args) {
        PriorityQueue<Integer> queue = new PriorityQueue<>(Comparator.naturalOrder());

        queue.offer(1);
        queue.offer(34);
        queue.offer(26);
        queue.offer(88);

        System.err.println(queue);
    }
}
