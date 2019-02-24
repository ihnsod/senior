package com.zxx.senior.demo;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author: Ihnsod
 * @create: 2018/12/21 01:55
 **/
public class TestLambad {

    private static List<Integer> integers = new ArrayList<>(1000000);

    static {
        for (int i = 0; i < 1000000; i++) {
            integers.add(i);
        }
    }

    public static void main(String[] args) {
        long l = System.nanoTime();

        List<Integer> integer = new ArrayList<>(900000);

        for (int i = 0; i < integers.size(); i++) {
            if (integers.get(i) > 10000) {
                integer.add(integers.get(i));
            }
        }
        long l1 = System.nanoTime();

        long l2 = System.nanoTime();

        List<Integer> collect = integers.stream().filter(integer1 -> integer1 > 10000).collect(Collectors.toList());

        long l3 = System.nanoTime();

        long start = System.nanoTime();

        List<Integer> collect1 = integers.parallelStream().filter(integer1 -> integer1 > 10000).collect(Collectors.toList());

        long end = System.nanoTime();


        System.err.println("普通" + (l1 - l));
        System.err.println("普通流" + (l3 - l2));
        System.err.println("并行流" + (end - start));
    }
}
