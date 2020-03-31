package com.zigar.api.entity.model;

import com.zigar.core.action.RequestInsertAction;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Data
public class RegisterUser implements Serializable {

    @NotBlank(message = "注册请输入用户名", groups = RequestInsertAction.class)
    private String username;
    @NotBlank(message = "注册请输入姓名", groups = RequestInsertAction.class)
    private String displayName;
    @NotBlank(message = "注册请输入密码", groups = RequestInsertAction.class)
    private String password;
    @NotBlank(message = "注册请输入重复密码", groups = RequestInsertAction.class)
    private String againPassword;
    private String vCode;

}
