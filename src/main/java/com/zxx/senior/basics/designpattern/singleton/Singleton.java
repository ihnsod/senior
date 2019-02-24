package com.zxx.senior.basics.designpattern.singleton;

/**
 * @author xuxiao zhang
 * @create 2018/06/20
 **/
public enum Singleton {
    SINGLETON;

    public static Singleton getSingleton() {
        return SINGLETON;
    }
}

class SingletonInnerClass {

    private SingletonInnerClass singletonInnerClass = new SingletonInnerClass();

    public static class Inner {
        public static final SingletonInnerClass getInstance() {
            return new SingletonInnerClass().singletonInnerClass;
        }
    }
}
