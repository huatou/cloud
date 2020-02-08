package com.zigar.user.system.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 自定义web配置类
 *
 * @author Zigar
 * @createTime 2019-10-12 14:46:51
 * @description
 */
@ConfigurationProperties(prefix = "zigar.web")
@Data
public class WebProperties {

    public static final String COMPONENT_UI = "componentUi";

    public static final String METRONIC_UI = "MetronicUi";

    //默认使用componentUI
    private String ui = COMPONENT_UI;

}
