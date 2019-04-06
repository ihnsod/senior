package com.Ihnsod.spring.model.dto;

import com.Ihnsod.spring.model.Convert;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.time.LocalDateTime;

/**
 * @author: Ihnsod
 * @create: 2019/03/23 22:13
 **/
@Data
@Builder
@ToString
public class UserDTO extends Convert {
    private static final long serialVersionUID = 1L;

    private Integer id;

    /**
     * 登陆名
     */
    private String loginName;
    /**
     * 登陆名
     */
    private String password;
    /**
     * 昵称
     */
    private String nickname;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 手机
     */
    private String phone;
    /**
     * IP地址
     */
    private String ip;

    /**
     * 创建者ID
     */
    private Integer createUid;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;
    /**
     * 修改时间
     */
    private LocalDateTime updateTime;
}
