package com.zigar.oauth2.security;//package com.zigar.user.system.security;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.zigar.api.entity.UserEntity;
import com.zigar.api.system.exception.BusinessLogicException;
import com.zigar.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Zigar
 * @createTime 2020-01-15 16:00:22
 * @description
 */

@Component
public class CustomUserDetailService implements UserDetailsService {

    @Autowired
    UserService userService;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        LambdaQueryWrapper<UserEntity> lambdaQueryWrapper = Wrappers.lambdaQuery();
        lambdaQueryWrapper.eq(UserEntity::getUsername, s);
        UserEntity localUser = userService.getOne(lambdaQueryWrapper);
        if (localUser == null) {
            throw new BusinessLogicException("该用户不存在");
        }
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_USER"));
        User user = new User(localUser.getUsername(), localUser.getPassword(), grantedAuthorities);
        return user;
    }


}
