package com.Ihnsod.basics.demo;


/**
 * @author: Ihnsod
 * @create: 2018/12/01 21:41
 **/
public class Demo1 {

    class SubClass extends SuperClass {
        public String name = "SubClass";
    }

    class SuperClass {
        public String name = "SuperClass";
    }

    public void test() {
        SuperClass clz = new SubClass();
        //你觉得这里输出什么?
        System.out.println(clz.name);
    }

}
