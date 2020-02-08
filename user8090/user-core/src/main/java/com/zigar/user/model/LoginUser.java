package com.zigar.user.model;

import com.zigar.user.system.Request.RequestLoginAction;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class LoginUser {


//    public static final String ROOT_USER_NAME = "root";
//    public static final String ROOT_PASSWORD = "$2a$10$x2sVbXt.8ky2XFyWSnf14.uLB/Eja2uvfleqgp7A2Sn6tR11dH1EO";//使用BCryptPasswordEncoder加密

    @NotBlank(message = "登录请输入用户名", groups = RequestLoginAction.class)
    private String username;
    @NotBlank(message = "登录请输入密码", groups = RequestLoginAction.class)
    private String password;
    private String captcha;

}
