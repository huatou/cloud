package com.zigar.user.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zigar.user.config.JdbcConfigBean;
import com.zigar.user.entity.User;
import com.zigar.user.model.Results;
import com.zigar.user.service.UserService;
import com.zigar.user.system.Request.RequestInsertAction;
import com.zigar.user.system.Request.RequestUpdateAction;
import com.zigar.user.system.utils.request.PageHelperUtils;
import com.zigar.user.system.utils.request.RequestUtils;
import org.apache.commons.lang3.StringUtils;
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
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private PageHelperUtils pageHelperUtils;

    @Autowired
    private JdbcConfigBean jdbcConfigBean;

    @GetMapping(value = "/test")
    public String test(){
        return this.jdbcConfigBean.toString();
    }

    @GetMapping("/zigar/user")
    Object list(HttpServletRequest httpServletRequest, User user) {
        Results<Page<User>> pageResults = pageHelperUtils.isPage(httpServletRequest);
        QueryWrapper<User> userQueryWrapper = Wrappers.query(user);
        userQueryWrapper.lambda().select(i -> !StringUtils.equals(i.getProperty(), "password"));
        if (pageResults.isSuccess()) {
            Page<User> userPage = userService.page(pageResults.getData(), userQueryWrapper);
            return Results.succeed(userPage);
        } else {
            List<User> list = userService.list(Wrappers.<User>lambdaQuery(user));
            return Results.succeed(list);
        }
    }

    @GetMapping("/zigar/user/{id}")
    Object get(@PathVariable String id) {
        User currentUser;
        if (StringUtils.equals(id, "current")) {
            currentUser = RequestUtils.getCurrentUser();
        } else {
            currentUser = userService.getOne(Wrappers.<User>lambdaQuery()
                    .select(User.class, i -> !StringUtils.equals(i.getProperty(), "password"))
                    .eq(User::getUserId, id));
        }
        return currentUser != null ? Results.succeed(currentUser) : Results.error("获取用户信息失败");
    }


    @PostMapping("/zigar/user")
    Object insert(@RequestBody @Validated(RequestInsertAction.class) User user) {
        User localUser = userService.getOne(Wrappers.<User>lambdaQuery()
                .eq(User::getUsername, user.getUsername()));
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
    Object update(@PathVariable Long id, @RequestBody @Validated(RequestUpdateAction.class) User user) {
        user.setUserId(id);
        User localUser = userService.getOne(Wrappers.<User>lambdaQuery().eq(User::getUserId, id));
        if (localUser == null) {
            return Results.error("用户不存在");
        }

        User localUserByUserName = userService.getOne(Wrappers.<User>lambdaQuery()
                .ne(User::getUserId, user.getUserId())
                .eq(User::getUsername, user.getUsername()));
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
        User localUser = userService.getOne(Wrappers.<User>lambdaQuery().eq(User::getUserId, id));
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

