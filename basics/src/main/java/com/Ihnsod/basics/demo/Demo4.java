package com.Ihnsod.basics.demo;

/**
 * @author: Ihnsod
 * @create: 2018/12/10 09:57
 **/
public class Demo4 {
    public static void main(String[] args) {
        add();
        getPerson();
        check();
    }

    public static int add() {
        int a = 100;
        int b = 200;
        int c = a * b;

        return c + a;
    }

    public static Person getPerson() {
        Person person = new Person();
        person.setId(1);
        person.setName("hello");
        return person;
    }

    public static boolean check() {
        Integer a = 2;
        return a instanceof Integer;
    }

    static class Person {
        Integer id;

        String name;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
