package com.zigar.core.utils;

public class StringUtils extends org.apache.commons.lang3.StringUtils {

    /**
     * 拼接字符串
     *
     * @param target
     * @param objects
     * @return
     */
    public static final String append(String target, Object... objects) {
        StringBuilder stringBuilder = new StringBuilder(target);
        for (Object o : objects) {
            if (o != null) {
                stringBuilder.append(o.toString());
            }
        }
        return stringBuilder.toString();
    }

}
