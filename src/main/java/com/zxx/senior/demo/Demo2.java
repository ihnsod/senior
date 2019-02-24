package com.zxx.senior.demo;

/**
 * @author: Aries
 * @create: 2018/12/01 21:46
 **/
public class Demo2 {
    {
        System.out.println("初始化代码");
    }

    Demo2() {
        System.out.println("构造器");
    }

    static {
        System.out.println("静态代码块");
    }

    //运行后输出结果?
    public static void main(String[] args) {

        new Demo2();
        new Demo2();
        new Demo2();

        {
            int a = 10;
            //10
            System.out.println("局部代码块");
        }
    }

}
