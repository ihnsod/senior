package com.Ihnsod.basics.demo;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: Ihnsod
 * @create: 2018/12/13 19:18
 **/
public class FanXing {
    public static <T> void show1(List<T> list) {
        for (Object object : list) {
            System.out.println(object.toString());
        }
    }

    public static void show2(List<? super Student> list) {
        for (Object object : list) {
            System.out.println(object);
        }
    }

    public static void test() {
        List list1 = new ArrayList<>();
//        list1.add(new Teacher("zxx", 18, 1));
//这里如果add(new Teacher(...));就会报错，因为我们已经给List 指定了数据类型为Student
        show1(list1);
        show2(list1);


    }

    public static void main(String[] args) {
        try {
            test();
        } catch (Exception e) {
            System.err.println("e");
        } finally {
            throw new RuntimeException();
        }

    }
}
