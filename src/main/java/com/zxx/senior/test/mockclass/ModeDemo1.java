package com.zxx.senior.test.mockclass;

import mockit.Injectable;
import mockit.Mocked;
import org.junit.Assert;
import org.junit.Test;

import java.util.Locale;

/**
 * @author: Ihnsod
 * @create: 2019/01/23 15:40
 **/
public class ModeDemo1 {

    //    @Injectable
    @Mocked
    private Book book;

    @Test
    public void mock1() {
        String name = book.getName();

        System.err.println(name);
    }


    // mocked 和 injectable 的区别
    @Test
    public void testMocked(@Mocked Locale locale) {
        // 静态方法不起作用了,返回了null
        Assert.assertNull(Locale.getDefault());
        // 非静态方法（返回类型为String）也不起作用了，返回了null
        Assert.assertNull(locale.getCountry());
        // 自已new一个，也同样如此，方法都被mock了
        Locale chinaLocale = new Locale("zh", "CN");
        Assert.assertNull(chinaLocale.getCountry());
    }

    @Test
    public void testInjectable(@Injectable Locale locale) {
        // 静态方法不mock
        Assert.assertNotNull(Locale.getDefault());
        // 非静态方法（返回类型为String）也不起作用了，返回了null,但仅仅限于locale这个对象
        Assert.assertNull(locale.getCountry());
        // 自已new一个，并不受影响
        Locale chinaLocale = new Locale("zh", "CN");
        Assert.assertEquals("CN", chinaLocale.getCountry());
    }
}
