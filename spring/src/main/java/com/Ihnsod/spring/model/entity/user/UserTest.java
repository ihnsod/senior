package com.Ihnsod.spring.model.entity.user;

import com.Ihnsod.spring.model.Convert;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author: Ihnsod
 * @create: 2019/03/23 18:42
 **/
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class UserTest extends Convert{

    private Integer id;

    private String username;

    private String password;

    private Integer enable;

}
