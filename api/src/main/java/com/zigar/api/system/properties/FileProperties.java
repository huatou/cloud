package com.zigar.api.system.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author Zigar
 * @createTime 2020-01-25 15:50:18
 * @description
 */

@ConfigurationProperties(prefix = FileProperties.PRE_FIX)
@Data
public class FileProperties {

    public static final String PRE_FIX = "zigar.file";

    private String localFilePath = "/Users/yangzihua/uploadFile";

    private String imageFilePath = "/images";

}