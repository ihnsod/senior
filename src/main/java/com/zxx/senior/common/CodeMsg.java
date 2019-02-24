package com.zxx.senior.common;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author: Ihnsod
 * @create: 2018/12/12 17:39
 **/
@Data
@AllArgsConstructor
public class CodeMsg {

    private Integer code;

    private String msg;

    public static CodeMsg BASE_SUCCESS = new CodeMsg(10000, "成功");
    public static CodeMsg  BASE_FAIL = new CodeMsg(10001, "失败");
    public static CodeMsg SECKILL_SUCCESS = new CodeMsg(20000, "秒杀成功!");
    public static CodeMsg SECKILL_FAIL = new CodeMsg(200001, "秒杀失败!");
    public static CodeMsg SECKILL_OVER = new CodeMsg(200003, "商品已经秒杀完毕!");
    public static CodeMsg SECKILL_LIMIT = new CodeMsg(200004, "前方拥堵,请稍候再试!");
    public static CodeMsg SECKILL_REPEAT = new CodeMsg(200005, "不能重复秒杀!");


}
