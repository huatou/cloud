package com.zigar.user.service.impl;

import com.zigar.user.entity.User;
import com.zigar.user.mapper.UserMapper;
import com.zigar.user.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zigar
 * @since 2020-01-15
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}
