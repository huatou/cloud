package com.zigar.user.system.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author Zigar
 * @createTime 2019-10-12 16:20:55
 * @description 密码配置类
 */

@ConfigurationProperties(prefix = "zigar.security.password")
@Data
public class PasswordProperties {

    //初始密码
    private String initPwd = "123456";

}
