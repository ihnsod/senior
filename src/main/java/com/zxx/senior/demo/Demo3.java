package com.zxx.senior.demo;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: Aries
 * @create: 2018/12/03 18:53
 **/
public class Demo3 {

    public static void main(String[] args) {
        while (true){
           new User();
        }
    }

    @Data
    static class User{
        private Integer age;

        private String name;

        private String address;


    }
}
