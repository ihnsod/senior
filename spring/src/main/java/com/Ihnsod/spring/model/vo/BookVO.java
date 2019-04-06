package com.Ihnsod.spring.model.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

/**
 * @author: Ihnsod
 * @create: 2019/03/24 00:23
 **/
@Data
@Valid
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BookVO {
    @NotBlank(message = "书名不能为null")
    private String bookName;

    public interface Create{

    }

    public interface Update{

    }
}
