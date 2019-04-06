package com.Ihnsod.spring.model.entity;

import lombok.Data;
import lombok.ToString;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @author: Ihnsod
 * @create: 2019/03/23 17:08
 **/
@Data
@ToString
@Accessors(chain = true)
public class Person {

    @NotNull(message = "id不能为空")
    private Integer id;
    @NotBlank
    @Size(min = 5,max = 20,message = "名字在5-20个字符之间")
    private String name;
    private Integer sex;

    private String province;

    private String city;

}
