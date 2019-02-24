package com.zxx.senior.demo;

import com.google.common.collect.Lists;
import lombok.Data;
import lombok.ToString;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.stream.Collectors;

/**
 * @author: Ihnsod
 * @create: 2018/12/13 19:18
 **/
@Data
@ToString
@Accessors(chain = true)
public class Student {

    @NotNull
    private String name;

    private Integer age;

    private Integer sex;

    public static void main(String[] args) {
        Student student = new Student().setAge(1).setSex(1);
        System.err.println(student);


        ArrayList<Student> objects = Lists.newArrayList();

        objects.stream().filter(student1 -> student1.getName() != null).collect(Collectors.toList());

        for (Student s : objects) {
            System.err.println(s.getSex());
        }

    }



}

@Data
@ToString
@Accessors(chain = true)
class User {
    private String name;

    private Integer age;

    private String address;
}
