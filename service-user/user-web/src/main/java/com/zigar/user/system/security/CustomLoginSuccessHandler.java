package com.zigar.user.system.security;//package com.zigar.user.system.security;
//
//import com.zigar.user.entity.User;
//import com.zigar.user.entity.UserLoginLog;
//import com.zigar.user.service.UserLoginLogService;
//import com.zigar.user.system.utils.request.RequestUtils;
//import com.zigar.user.system.utils.jwt.JwtToken;
//import com.zigar.user.system.utils.jwt.JwtTokenUtil;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
//import org.springframework.stereotype.Component;
//
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//
///**
// * Created by yangzihua on 2019/3/31.
// */
//
//@Component
//public class CustomLoginSuccessHandler implements AuthenticationSuccessHandler {
//
//    @Autowired
//    private CaptchaCacheHandler captchaCacheHandler;
//
//    @Autowired
//    private UserLoginLogService userLoginLogService;
//
//    @Autowired
//    private JwtTokenUtil jwtTokenUtil;
//
//    @Override
//    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
//
//        /**
//         * 用户名密码验证成功后
//         * 1.生成token
//         * 2.保存token至redis
//         * 3.记录登录日志
//         * 4.清除该用户对应的验证码
//         * 5.返回登录成功消息和token到前台
//         */
//        User currentUser = RequestUtils.getCurrentUser();
//        JwtToken jwtToken = jwtTokenUtil.generateToken(currentUser);
//
//        String userAgent = httpServletRequest.getHeader("user-agent");
//        UserLoginLog userLoginLog = new UserLoginLog();
//        userLoginLog.setUserId(currentUser.getUserId());
//        userLoginLog.setUserAgent(userAgent);
//        userLoginLogService.save(userLoginLog);
//
//        captchaCacheHandler.clearCaptcha(currentUser.getUsername());
//
////        Results results = new Results(true, "登录成功", jwtToken.getToken());
////        httpServletResponse.setContentType("application/json;charset=utf-8");
////        ServletOutputStream servletOutputStream = httpServletResponse.getOutputStream();
////        servletOutputStream.write(JSON.toJSONString(results).getBytes());
//
//    }
//
//
//}
