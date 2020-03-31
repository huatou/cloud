//package com.zigar.user.controller;
//
//import com.baomidou.mybatisplus.core.toolkit.Wrappers;
//import com.zigar.core.action.RequestLoginAction;
//import com.zigar.api.entity.UserEntity;
//import com.zigar.api.entity.UserLoginLog;
//import com.zigar.api.entity.model.LoginUser;
//import com.zigar.api.entity.model.Results;
//import com.zigar.user.service.UserLoginLogService;
//import com.zigar.user.service.UserService;
//import com.zigar.user.system.security.CaptchaCacheHandler;
//import com.zigar.user.system.security.ImageCode;
//import com.zigar.user.system.utils.jwt.JwtToken;
//import com.zigar.user.system.utils.jwt.JwtTokenUtil;
//import com.zigar.user.system.utils.jwt.PassToken;
//import com.zigar.user.system.utils.request.RequestUtils;
//import org.apache.commons.lang.StringUtils;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.validation.annotation.Validated;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RestController;
//
//import javax.servlet.http.HttpServletRequest;
//import java.io.IOException;
//
///**
// * @author Zigar
// * @createTime 2020-01-19 10:36:25
// * @description
// */
//
//@RestController
//public class HomeRestController {
//
//    Logger logger = LoggerFactory.getLogger(this.getClass());
//
//    @Autowired
//    private UserService userService;
//
//    @Autowired
//    private PasswordEncoder passwordEncoder;
//
//    @Autowired
//    private JwtTokenUtil jwtTokenUtil;
//
//    @Autowired
//    private CaptchaCacheHandler captchaCacheHandler;
//
//    @Autowired
//    private UserLoginLogService userLoginLogService;
//
//
//    @PassToken
//    @PostMapping("/zigar/login")
//    Results login(@RequestBody @Validated(RequestLoginAction.class) LoginUser loginUser, HttpServletRequest httpServletRequest) throws IOException {
//
//        /**
//         *
//         * 检查当前用户是否存在
//         * 检查当前用户是否有登录失败的情况，即检查redis是否有该用户对应的验证码，有则需要检查验证码是否正确，验证码不正确则需要生成新的验证码
//         * 判断用户密码是否正确，不正确则需要生成验证码并把该验证码保存在redis，最后把验证码返回到前台
//         * 验证密码正确后，记录登录日志，生成token，并保存当前用户对象在SecurityContextHolder，在redis删除对用的验证码
//         * 返回token到前台
//         *
//         */
//
//        UserEntity currentUser = userService.getOne(Wrappers.<UserEntity>lambdaQuery().eq(UserEntity::getUsername, loginUser.getUsername()));
//        if (currentUser == null) {
//            return Results.error("用户不存在");
//        } else {
//            String cacheCaptcha = captchaCacheHandler.getCaptchaCacheByUsername(loginUser.getUsername());
//            if (!StringUtils.isEmpty(cacheCaptcha)) {
//                logger.info("用户为：" + loginUser.getUsername() + "的验证码为：" + cacheCaptcha + "；输入的验证码为：" + loginUser.getCaptcha());
//                if (StringUtils.isEmpty(loginUser.getCaptcha()) || !StringUtils.equalsIgnoreCase(loginUser.getCaptcha(), cacheCaptcha)) {
//                    String imageCode = loginFailHandler(loginUser);
//                    return Results.error("验证码不正确", imageCode);
//                }
//            }
//            boolean isValidated = passwordEncoder.matches(loginUser.getPassword(), currentUser.getPassword());
//            if (isValidated) {
//                String token = loginSuccessHandler(httpServletRequest, currentUser);
//                return new Results(true, "登录成功", token);
//            } else {
//                String imageCode = loginFailHandler(loginUser);
//                return Results.error("密码错误", imageCode);
//            }
//        }
//    }
//
//
//    private String loginSuccessHandler(HttpServletRequest httpServletRequest, UserEntity currentUser) {
//
//        RequestUtils.setCurrentUser(currentUser);
//
//        String userAgent = httpServletRequest.getHeader("user-agent");
//        UserLoginLog userLoginLog = new UserLoginLog();
//        userLoginLog.setUserId(currentUser.getUserId());
//        userLoginLog.setUserAgent(userAgent);
//        userLoginLogService.save(userLoginLog);
//
//        captchaCacheHandler.clearCaptcha(currentUser.getUsername());
//
//        JwtToken jwtToken = jwtTokenUtil.generateToken(currentUser);
//        return jwtToken.getToken();
//    }
//
//    private String loginFailHandler(LoginUser loginUser) throws IOException {
//        ImageCode imageCode = captchaCacheHandler.setCaptchaCache(loginUser.getUsername());
//        logger.info("用户为：" + loginUser.getUsername() + "的验证码为：" + imageCode.getCode());
//        return imageCode.getImageBase64();
//    }
//
//    @PostMapping("/zigar/logout")
//    Object logout() {
//
//        /**
//         *
//         * 删除当前用户在SecurityContextHolder中的缓存
//         *
//         */
//
//        RequestUtils.clearCurrentUser();
//        return Results.succeed("登出成功");
//
//    }
//
//}
