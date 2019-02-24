package com.zxx.senior.ownspring.servlet;

import com.zxx.senior.ownspring.annotation.*;
import com.zxx.senior.ownspring.controller.VController;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.*;

/**
 * @author: Aries
 * @create: 2018/12/03 21:58
 **/
public class ZDispatcherServlet extends HttpServlet {

    List<String> classNames = new ArrayList<>(16);

    // bean的初始化
    Map<String, Object> beans = new HashMap<>(16);

    // 方法映射 map  根据路径找到要执行的方法
    Map<String, Object> handlerMap = new HashMap<>(16);


    public void init(ServletConfig config) {
        //把所有的bean 扫描到
        scanPackage("com.zxx.senior.ownspring");
        //根据扫描的全类名实例化
        doInstance();
        //给类属性赋值
        doIoC();
        //绑定mapping 映射
        buildMapping();
    }

    private void buildMapping() {
        if (getBeansIsNull()) {
            return;
        }

        Iterator<Map.Entry<String, Object>> iterator = beans.entrySet().iterator();

        while (iterator.hasNext()) {
            Map.Entry<String, Object> next = iterator.next();

            Object instance = next.getValue();

            Class<?> clazz = instance.getClass();


            if (clazz.isAnnotationPresent(ZRequestMapping.class)) {

                ZRequestMapping zRequestMapping = clazz.getAnnotation(ZRequestMapping.class);
                String classPath = zRequestMapping.value();

                Method[] methods = clazz.getMethods();
                for (Method method : methods) {
                    if (method.isAnnotationPresent(ZRequestMapping.class)) {
                        ZRequestMapping mapping = method.getAnnotation(ZRequestMapping.class);
                        String methodPath = mapping.value();

                        handlerMap.put(classPath + method, instance);
                    } else {
                        continue;
                    }
                }
            } else {
                continue;
            }
        }
    }

    private void doIoC() {
        if (getBeansIsNull()) {
            return;
        }
        Iterator<Map.Entry<String, Object>> iterator = beans.entrySet().iterator();

        while (iterator.hasNext()) {
            Map.Entry<String, Object> next = iterator.next();
            Object instance = next.getValue();

            Class<?> clazz = instance.getClass();

            if (clazz.isAnnotationPresent(ZController.class)) {
                Field[] fields = clazz.getDeclaredFields();

                for (Field field : fields) {
                    if (field.isAnnotationPresent(ZAutowired.class)) {
                        ZAutowired autowired = field.getAnnotation(ZAutowired.class);
                        String value = autowired.value();
                        // 打开字段private权限 暴力反射
                        field.setAccessible(true);
                        try {
                            field.set(instance, value);
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        }
                    } else {
                        continue;
                    }
                }
            } else {
                continue;
            }
        }
    }

    private boolean getBeansIsNull() {
        return beans == null || beans.isEmpty();
    }

    private boolean getClassNameIsNull() {
        return classNames == null || classNames.isEmpty();
    }

    private void doInstance() {
        if (getClassNameIsNull()) {
            return;
        }
        for (String s : classNames) {
            String replace = s.replace(".class", "");
            try {
                Class<?> clazz = Class.forName(replace);
                if (clazz.isAnnotationPresent(ZController.class)) {
                    Object instance = clazz.newInstance();
                    ZRequestMapping mapping = clazz.getAnnotation(ZRequestMapping.class);
                    String value = mapping.value();
                    beans.put(value, instance);
                } else if (clazz.isAnnotationPresent(ZService.class)) {
                    Object instance = clazz.newInstance();
                    ZService service = clazz.getAnnotation(ZService.class);
                    String value = service.value();
                    beans.put(value, instance);
                } else {
                    continue;
                }
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            }
        }

    }

    private void scanPackage(String basePackage) {
        URL url = this.getClass().getClassLoader().getResource("/" + basePackage.replaceAll("\\.", "/"));
        String fileStr = url.getFile();

        File file = new File(fileStr);
        String[] list = file.list();

        Arrays.stream(list).forEach(path -> {
            File filePath = new File(fileStr + path);
            if (filePath.isDirectory()) {
                scanPackage(basePackage + "." + path);
            } else {
                classNames.add(basePackage + "." + filePath.getName());
            }
        });
    }

    private Object[] hand(HttpServletRequest request, HttpServletResponse response, Method method) {

        Class<?>[] parameterTypes = method.getParameterTypes();

        Object args[] = new Object[parameterTypes.length];

        int arg = 0, index = 0;
        for (Class clazz : parameterTypes) {
            if (ServletRequest.class.isAssignableFrom(clazz)) {
                args[arg++] = request;
            }
            if (ServletResponse.class.isAssignableFrom(clazz)) {
                args[arg++] = response;
            }

            //获取方法上的所有注解
            Annotation[] annotations = method.getParameterAnnotations()[0];
            if (annotations.length > 0) {
                for (Annotation param : annotations) {
                    if (ZRequestParam.class.isAssignableFrom(param.getClass())) {
                        ZRequestParam zRequestParam = (ZRequestParam) param;
                        //找到注解中的age 和 name
                        args[arg++] = request.getParameter(zRequestParam.value());
                    }
                }
            }
            index++;
        }
        return args;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String requestURI = req.getRequestURI(); // zxx/query

        String contextPath = req.getContextPath();

        String path = requestURI.replace(contextPath, "");

        Method method = (Method) handlerMap.get(path);

        VController o = (VController) beans.get("/" + path.split("/")[1]);

        Object arg[] = hand(req, resp, method);

        try {
            method.invoke(o, arg);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

    }

}
