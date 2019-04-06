package com.Ihnsod.spring.model.vo;

import com.Ihnsod.common.Regex;
import com.Ihnsod.spring.enums.user.StatusEnum;
import com.Ihnsod.spring.model.Convert;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.util.List;

/**
 * @author: Ihnsod
 * @create: 2019/03/23 22:13
 **/
@Data
@Builder
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class UserVO extends Convert {

    @ApiModelProperty(name = "测试验证组")
    @NotBlank(groups = {Create.class}, message = "测试验证group")
    private String name;

    @ApiModelProperty(notes = "登陆名")
    @NotBlank(groups = {Create.class, Update.class}, message = "用户名不能为空")
    private String loginName;
    @ApiModelProperty(notes = "昵称")
    @NotBlank(groups = {Create.class, Update.class}, message = "昵称不能为空")
    private String nickname;
    @Email(groups = {Create.class, Update.class}, message = "邮箱格式不正确")
    @ApiModelProperty(notes = "邮箱")
    private String email;
    @Pattern(groups = {Create.class, Update.class}, regexp = Regex.PHONE, message = "手机号码格式不正确")
    @ApiModelProperty(notes = "手机号")
    private String phone;
    @NotNull(groups = Status.class, message = "用户状态不能为空")
    @ApiModelProperty(notes = "状态:0：禁用 1：正常")
    private StatusEnum status;
    @ApiModelProperty(notes = "用户角色ID")
    @NotEmpty(groups = {Create.class, Update.class}, message = "用户角色不能为空")
    private List<Integer> roleIds;

    @Valid
    private BookVO bookVO;

    public interface Create extends Default{

    }

    public interface Update extends Default{

    }

    public interface Status extends Default{

    }

    public interface Default{

    }

}
