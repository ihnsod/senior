//package com.Ihnsod.spring.controller.transactional;
//
//
//import com.Ihnsod.common.result.BaseResult;
//import com.Ihnsod.spring.pojo.transactional.Student;
//import com.Ihnsod.spring.service.transactional.TransactionalServiceOne;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//import sun.audio.AudioPlayer;
//import sun.audio.AudioStream;
//
//import java.io.ByteArrayOutputStream;
//import java.io.File;
//import java.io.IOException;
//import java.io.InputStream;
//import java.net.URL;
//import java.util.concurrent.TimeUnit;
//import java.util.zip.ZipEntry;
//import java.util.zip.ZipOutputStream;
//
///**
// * @author: Ihnsod
// * @create: 2019/01/02 22:27
// **/
//@RestController
//@RequestMapping("transactional")
//public class TransactionalController {
//
//    @Autowired
//    private TransactionalServiceOne transactionalServiceOne;
//
//    @RequestMapping("/demo1")
//    public BaseResult transactionalDemo1() {
//
//        transactionalServiceOne.transactionalOne();
//
//        return BaseResult.successPojo(null);
//    }
//
//
//    @RequestMapping("/demo2")
//    public BaseResult transactionalDemo2() {
//
//        transactionalServiceOne.transactionalTwo();
//
//        return BaseResult.successPojo(null);
//    }
//
//    @RequestMapping("/demo3")
//    public BaseResult transactionalDemo3() {
//
//        transactionalServiceOne.transactionalTwo();
//
//        return BaseResult.successPojo(null);
//    }
//
//    @RequestMapping("/demo8")
//    public BaseResult transactionalDemo4() {
//
//        transactionalServiceOne.transactionalTwo();
//
//        return BaseResult.successPojo(null);
//    }
//
//    @PostMapping("/student")
//    public BaseResult opDemo(Student student) {
//        System.err.println(student.toString());
//        return BaseResult.successPojo(student);
//    }
//
//    public static void main(String[] args) throws IOException, InterruptedException {
//        java.awt.Toolkit.getDefaultToolkit().beep();
//        File a = new File("C:\\Users\\Ihnsod\\Desktop\\Aimer - 茜さす.wav");
//        URL url = a.toURI().toURL();
//        System.out.println(url);
//        InputStream inputStream = url.openStream();
//        AudioStream audioStream = new AudioStream(inputStream);
//        AudioPlayer.player.start(audioStream);
//        TimeUnit.SECONDS.sleep(10);
//        Thread.currentThread();
//
//
//        ZipOutputStream outputStream = new ZipOutputStream(new ByteArrayOutputStream());
//
//        ZipEntry zipEntry = new ZipEntry(String.valueOf(new File("path")));
//
//        outputStream.putNextEntry(zipEntry);
//        outputStream.closeEntry();
//
//
//    }
//}
