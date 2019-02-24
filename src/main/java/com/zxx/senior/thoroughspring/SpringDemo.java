package com.zxx.senior.thoroughspring;

import com.google.common.collect.Lists;
import com.zxx.senior.demo.Student;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: Ihnsod
 * @create: 2018/12/20 22:50
 **/
@RestController
@RequestMapping("test/controller")
public class SpringDemo {

    @CrossOrigin(origins = "http://localhost:63343",maxAge = 3600)
    @GetMapping("/test")
    public List<Student> test() {
        ArrayList<Student> objects = Lists.newArrayList();

        objects.add(new Student().setAge(18).setSex(1).setName("hello"));
        objects.add(new Student().setAge(19).setSex(0).setName("world"));
        objects.add(new Student().setAge(20).setSex(1).setName("me"));
        objects.add(new Student().setAge(21).setSex(0).setName("you"));

        return objects;
    }
}
