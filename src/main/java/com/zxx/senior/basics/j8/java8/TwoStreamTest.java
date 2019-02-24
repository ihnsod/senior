package com.zxx.senior.basics.j8.java8;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author xuxiao zhang
 * @create 2018/09/20
 **/
public class TwoStreamTest {

    public static void main(String[] args) {
        User user1 = new User(1, "zxx", "bj");
        User user2 = new User(2, "zxxx", "nj");
        Person person1 = new Person(3, "zxxxx");
        Person person2 = new Person(3, "zxxxxx");

        List<User> list1 = new ArrayList<>();
        list1.add(user1);
        list1.add(user2);

        List<Person> list2 = new ArrayList<>();
        list2.add(person1);
        list2.add(person2);


        list1.stream().collect(Collectors.toList());

        List<Object> collect = Stream.concat(list2.stream(), list1.stream()).collect(Collectors.toList());

        System.err.println(collect);


        List<String> strings = new ArrayList<>();

        strings.add("hello");
        strings.add("world");

        System.err.println(strings.toString());
    }

    static class User {
        Integer id;
        String uName;
        String address;

        public User(Integer id, String uName, String address){
            this.id = id;
            this.uName = uName;
            this.address = address;
        }

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        @Override
        public String toString() {
            return "User{" +
                    "id=" + id +
                    ", uName='" + uName + '\'' +
                    ", address='" + address + '\'' +
                    '}';
        }
    }

    static class Person{
        Integer id;
        String pName;

        public Person(Integer id, String pName){
            this.id = id;
           this.pName = pName;
        }

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        @Override
        public String toString() {
            return "User{" +
                    "id=" + id +
                    ", uName='" + pName + '\'' +
                    '}';
        }
    }

}
