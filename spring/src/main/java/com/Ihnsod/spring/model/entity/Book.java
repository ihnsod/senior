package com.Ihnsod.spring.model.entity;

import lombok.Data;
import lombok.ToString;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author: Ihnsod
 * @create: 2019/03/23 19:17
 **/
@Data
@Valid
@ToString
public class Book {
    @NotNull
    private Integer bookId;
    @NotBlank
    private String bookName;
}
