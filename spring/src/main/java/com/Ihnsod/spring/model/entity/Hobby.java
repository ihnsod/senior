package com.Ihnsod.spring.model.entity;

import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @author: Ihnsod
 * @create: 2019/03/23 18:45
 **/
@Data
@Valid
public class Hobby {

    @NotNull
    private Integer hobbyId;
    @NotBlank
    @Size(min = 5,max = 15)
    private String hobbyName;

}
