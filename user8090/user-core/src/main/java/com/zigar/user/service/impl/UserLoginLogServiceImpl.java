package com.zigar.user.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zigar.user.entity.UserLoginLog;
import com.zigar.user.mapper.UserLoginLogMapper;
import com.zigar.user.service.UserLoginLogService;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zigar
 * @since 2020-01-17
 */
@Service
public class UserLoginLogServiceImpl extends ServiceImpl<UserLoginLogMapper, UserLoginLog> implements UserLoginLogService {

}
