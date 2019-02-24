package com.zxx.senior.basics.j8;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class PackAndBasics {
    @Test
    public void equals() {
        /*Integer a = 3;
        System.err.println(a.equals(3));//false 看源码得知如果不是Integer就会是false 是包装类的基本类型也可以
        Character b = 'V';
        System.err.println(b.equals('V'));
        Boolean flag = true;
        System.err.println(flag.equals(true));*/

        Integer[] arr = {1, 2, 3, 4, 5};

        int[] ints = Arrays.stream(arr).mapToInt(Integer::intValue).toArray();

        List<Integer> collect = Arrays.stream(ints).boxed().collect(Collectors.toList());

        System.err.println(collect);


        for (int i = 0; i < ints.length; i++) {
            System.err.println(ints[i]);
        }
    }

    @Test
    public void packToBasic() {
       /* Integer a = 6;
        int i = a.intValue();*/

        scan:
        {
            for (int j = 0; j < 10; j++) {
                for (int k = 0; k < 3; k++) {
                    if (k == 1) {
                        break scan;
                    }
                }
            }
        }

    }


}
