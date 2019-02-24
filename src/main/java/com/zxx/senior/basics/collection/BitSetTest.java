package com.zxx.senior.basics.collection;

import java.util.BitSet;

/**
 * @author: Elite
 * @date: 2018/6/18
 */
public class BitSetTest {
    public static void main(String[] args) {
        BitSet bitSet = new BitSet(10);
        for (int i = 0; i < 11; i++) {
            bitSet.set(i);
        }

        System.err.println(bitSet.toString());
    }
}

