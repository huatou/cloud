package com.zigar.file.feign.fallback;

import com.zigar.file.entity.User;
import com.zigar.file.feign.UserFeignClient;
import com.zigar.file.model.Results;
import org.springframework.stereotype.Component;

/**
 * @author Zigar
 * @createTime 2020-01-30 20:01:40
 * @description
 */

@Component
public class UserServiceFallback implements UserFeignClient {

    @Override
    public Results<User> queryUserById(Long id) {
        return Results.error();
    }

}
