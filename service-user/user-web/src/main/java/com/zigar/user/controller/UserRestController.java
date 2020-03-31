package com.zigar.user.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.zigar.api.entity.UserEntity;
import com.zigar.core.action.RequestInsertAction;
import com.zigar.core.action.RequestUpdateAction;
import com.zigar.core.model.Page;
import com.zigar.core.model.Results;
import com.zigar.core.utils.PageHelperUtils;
import com.zigar.user.config.ApplicationYmlBean;
import com.zigar.user.service.IUserService;
import com.zigar.user.system.utils.request.RequestUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author zigar
 * @since 2020-01-15
 */
@RestController
public class UserRestController {

    @Autowired
    private IUserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private PageHelperUtils pageHelperUtils;

    @Autowired
    private ApplicationYmlBean applicationYmlBean;

    @GetMapping(value = "/test")
    public String test() {
        return this.applicationYmlBean.toString();
    }

    @GetMapping("/zigar/user")
    Object list(HttpServletRequest httpServletRequest, UserEntity user) {
        Results<Page<UserEntity>> pageResults = pageHelperUtils.isPage(httpServletRequest);
        QueryWrapper<UserEntity> userQueryWrapper = Wrappers.query(user);
        userQueryWrapper.lambda().select(i -> !StringUtils.equals(i.getProperty(), "password"));
        if (pageResults.isSuccess()) {
            Page<UserEntity> userPage = userService.page(pageResults.getData(), userQueryWrapper);
            return Results.succeed(userPage);
        } else {
            List<UserEntity> list = userService.list(Wrappers.<UserEntity>lambdaQuery(user));
            return Results.succeed(list);
        }
    }

    @GetMapping("/zigar/user/{id}")
    Object get(@PathVariable String id) {
        UserEntity currentUser;
        if (StringUtils.equals(id, "current")) {
            currentUser = RequestUtils.getCurrentUser();
        } else {
            currentUser = userService.getOne(Wrappers.<UserEntity>lambdaQuery()
                    .select(UserEntity.class, i -> !StringUtils.equals(i.getProperty(), "password"))
                    .eq(UserEntity::getUserId, id));
        }
        return currentUser != null ? Results.succeed(currentUser) : Results.error("获取用户信息失败");
    }

    @GetMapping("/zigar/user/username/{username}")
    Object getUserByUsername(@PathVariable String username) {
        UserEntity userEntity = userService.getOne(Wrappers.<UserEntity>lambdaQuery()
                .eq(UserEntity::getUsername, username));
        return userEntity;
    }


    @PostMapping("/zigar/user")
    Object insert(@RequestBody @Validated(RequestInsertAction.class) UserEntity user) {
        UserEntity localUser = userService.getOne(Wrappers.<UserEntity>lambdaQuery()
                .eq(UserEntity::getUsername, user.getUsername()));
        if (localUser != null) {
            return Results.error("用户名【" + user.getUsername() + "】已存在");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        boolean result = userService.save(user);
        if (result) {
            return Results.succeed(user);
        } else {
            return Results.error("插入用户失败");
        }
    }

    @PutMapping("/zigar/user/{id}")
    Object update(@PathVariable Long id, @RequestBody @Validated(RequestUpdateAction.class) UserEntity user) {
        user.setUserId(id);
        UserEntity localUser = userService.getOne(Wrappers.<UserEntity>lambdaQuery().eq(UserEntity::getUserId, id));
        if (localUser == null) {
            return Results.error("用户不存在");
        }

        UserEntity localUserByUserName = userService.getOne(Wrappers.<UserEntity>lambdaQuery()
                .ne(UserEntity::getUserId, user.getUserId())
                .eq(UserEntity::getUsername, user.getUsername()));
        if (localUserByUserName != null) {
            return Results.error("用户名【" + user.getUsername() + "】已存在");
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        boolean result = userService.saveOrUpdate(user);
        if (result) {
            return Results.succeed("更新用户成功");
        } else {
            return Results.error("更新用户失败");
        }
    }

    @DeleteMapping("/zigar/user/{id}")
    Object delete(@PathVariable Long id) {
        UserEntity localUser = userService.getOne(Wrappers.<UserEntity>lambdaQuery().eq(UserEntity::getUserId, id));
        if (localUser == null) {
            return Results.error("用户名不存在");
        }
        boolean result = userService.removeById(id);
        if (result) {
            return Results.succeed("删除用户成功");
        } else {
            return Results.error("删除用户失败");
        }
    }


}

