package com.Ihnsod.basics.demo;

/**
 * @author: Ihnsod
 * @create: 2019/01/17 12:17
 **/
public class Demo1o {

    // 查看一个对象是否是一个类文件的实例
    public static void main(String[] args) {
        System.err.println(check(Teacher.class, "hehe"));
//        System.err.println(check(Teacher.class, new Teacher("h", 13, 1)));
    }


    private static boolean check(Class<?> clazz, Object msg) {
        return clazz.isInstance(msg);
    }
}
