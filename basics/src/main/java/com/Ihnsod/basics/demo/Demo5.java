package com.Ihnsod.basics.demo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * @author: Ihnsod
 * @create: 2018/12/26 23:53
 **/
public class Demo5 {
    public static void main(String[] args) {

        List<Student> students = new ArrayList<>();

        students.add(new Student().setAge(19).setName("hello").setSex(1));
        students.add(new Student().setAge(null).setName("hello").setSex(1));
        students.add(new Student().setAge(15).setName("hello").setSex(1));
        students.add(null);

        List<Student> stus = Optional.ofNullable(students).orElse(Collections.emptyList());

        Optional<Integer> reduce = stus.stream().filter(student -> student != null &&
                Optional.ofNullable(student.getAge()).orElse(0) > 18)
                .map(Student::getAge).reduce((x, y) -> x + y);


        reduce.ifPresent(integer -> {
            Student student = new Student().setAge(integer);
            System.err.println(student);
        });

        Integer integer = reduce.orElseGet(() -> 12);

        System.err.println(integer);

    }
}
