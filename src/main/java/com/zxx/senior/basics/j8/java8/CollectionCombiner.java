package com.zxx.senior.basics.j8.java8;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author xuxiao zhang
 * @create 2018/09/20
 **/
public class CollectionCombiner<E, K, T> {

    public static <E, K, T> List<T> join(List<E> list1, String keyMethodName1, Class<E> c1, List<K> list2, String keyMethodName2, Class<K> c2) {
        List<T> list = new ArrayList<>();
        list1.stream().forEach(e -> {
            list2.stream().forEach(k -> {
                try {
                    Method method = c1.getMethod(keyMethodName1);
                    Object invoke = method.invoke(e);
                    Method method1 = c2.getMethod(keyMethodName2);
                    Object invoke1 = method1.invoke(k);
                    if (Objects.equals(invoke, invoke1)) {
                     /*   List<Object> collect = Arrays.stream(c1.getMethods()).map(method2 -> {
                            try {
                                return method2.invoke(e);
                            } catch (IllegalAccessException e1) {
                                e1.printStackTrace();
                            } catch (InvocationTargetException e1) {
                                e1.printStackTrace();
                            }
                        }).collect(Collectors.toList());*/
                    }

                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            });
        });
        return list;
    }


}
