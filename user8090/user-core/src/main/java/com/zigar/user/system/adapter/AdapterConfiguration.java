package com.zigar.user.system.adapter;

import com.zigar.user.system.properties.WebProperties;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Zigar
 * @createTime 2020-01-19 15:54:09
 * @description
 */

@Configuration
public class AdapterConfiguration {

    @Bean
    UiAdapter uiAdapter(WebProperties webProperties) {
        if (StringUtils.equals(webProperties.getUi(), WebProperties.METRONIC_UI)) {
            return new MetronicUiAdapter();
        }
        return new ComponentUiAdapter();
    }

}
