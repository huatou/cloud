package com.zigar.core.ui;

import com.zigar.core.properties.WebProperties;
import com.zigar.core.utils.StringUtils;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Zigar
 * @createTime 2020-03-06 20:12:50
 * @description
 */

@Configuration
public class UiConfiguration {

    @Bean
    @ConditionalOnMissingBean
    UiAdapter uiAdapter(WebProperties webProperties) {
        if (StringUtils.equals(webProperties.getUi(), WebProperties.METRONIC_UI)) {
            return new MetronicUiAdapter();
        }
        return new ComponentUiAdapter();
    }

}
