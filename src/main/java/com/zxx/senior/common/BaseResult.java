package com.zxx.senior.common;

import lombok.Data;
import org.aspectj.apache.bcel.classfile.Code;

import java.util.Collection;
import java.util.Map;

/**
 * @author: Aries
 * @create: 2018/08/27 19:52
 **/
@Data
public class BaseResult<E, T, K, V> {
    /**
     * 状态码
     */
    private Integer code;
    /**
     * 响应结果
     */
    private String msg;
    /**
     * 集合泛型结果
     */
    private Collection<E> collection;
    /**
     * map泛型结果
     */
    private Map<K, V> map;

    /**
     * pojo泛型结果 在此和Object相同
     */
    private T pojo;

    public BaseResult(Collection<E> collection) {
        this.collection = collection;
        this.code = BaseConstants.SUCCESS_CODE;
        this.msg = BaseConstants.SUCCESS;
    }

    public BaseResult(Map<K, V> map) {
        this.map = map;
        this.code = BaseConstants.SUCCESS_CODE;
        this.msg = BaseConstants.SUCCESS;
    }

    public BaseResult(T pojo) {
        this.pojo = pojo;
        this.code = BaseConstants.SUCCESS_CODE;
        this.msg = BaseConstants.SUCCESS;
    }

    public BaseResult(CodeMsg msg) {
        this.msg = msg.getMsg();
        this.code = msg.getCode();
    }

    public static <T> BaseResult successCollection(Collection<T> collection) {
        return new BaseResult(collection);
    }

    public static <K, V> BaseResult successMap(Map<K, V> map) {
        return new BaseResult(map);
    }

    public static <T> BaseResult successPojo(T pojo) {
        return new BaseResult(pojo);
    }

    public static BaseResult error(CodeMsg msg) {
        return new BaseResult(msg);
    }

}
