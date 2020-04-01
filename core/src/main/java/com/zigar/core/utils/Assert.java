package com.zigar.core.utils;

import org.springframework.lang.Nullable;

public class Assert extends org.springframework.util.Assert {

    public static void notNull(@Nullable Object object, String message) {
        if (object == null) {
            throw new IllegalArgumentException(message);
        } else if (object instanceof String) {
            String str = (String) object;
            if (StringUtils.isEmpty(str)) {
                throw new IllegalArgumentException(message);
            }
        }
    }

}
