package com.zigar.user.system.utils.jwt;///*******************************************************************************
// * 版权所属 2019 Liang.Xia 及 原作者
// * 您可以在 Apache License 2.0 版下获得许可副本，
// * 同时必须保证分发的本软件是在“原始”的基础上分发的。
// * 除非适用法律要求或书面同意。
// *
// * http://www.apache.org/licenses/LICENSE-2.0
// *
// * 请参阅许可证中控制权限和限制的特定语言。
// ******************************************************************************/
//
//package com.huatou.utils.jwt;
//
//import com.alibaba.fastjson.JSON;
//import com.huatou.Confog.Config;
//import com.huatou.entity.UserEntity;
//import com.huatou.model.Results;
//import com.huatou.system.cache.CacheName;
//import com.huatou.utils.String.StringUtils;
//import io.jsonwebtoken.ExpiredJwtException;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.InitializingBean;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.cache.Cache;
//import org.springframework.data.redis.cache.RedisCacheManager;
//import org.springframework.stereotype.Component;
//import org.springframework.web.filter.OncePerRequestFilter;
//
//import javax.servlet.FilterChain;
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//
//@Component
//public class JwtAuthTokenFilter extends OncePerRequestFilter implements InitializingBean {
//
//    final Logger logger = LoggerFactory.getLogger(this.getClass());
//
//    @Autowired
//    RedisCacheManager redisCacheManager;
//
//    private JwtTokenUtil jwtTokenUtil;
//    private Cache cache;
//
//    @Override
//    public void afterPropertiesSet() throws ServletException {
//        jwtTokenUtil = new JwtTokenUtil();
//        cache = redisCacheManager.getCache(CacheName.JWT_TOKEN);
//    }
//
//    @Override
//    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
//            throws ServletException, IOException {
//
//        String jwtToken = request.getHeader(Config.TOKEN_HEADER);
//
//        String username = null;
//        if (!StringUtils.isEmpty(jwtToken)) {
//            try {
//                username = jwtTokenUtil.getUsernameFromToken(jwtToken);
//            } catch (ExpiredJwtException e) {
//                tokenExpire(response,"使用的令牌已经过期。");
//            } catch (RuntimeException e) {
//                tokenExpire(response,"无法解析请求的令牌。");
//            }
//            if (username == null) {
//                return;
//            }
//        }
//
//        if (username != null) {
//
//            if (logger.isDebugEnabled()) {
//                logger.debug("没有身份认证信息，开始授权用户。{}", username);
//            }
//
//            String cacheKey = jwtTokenUtil.getUserIdFromToken(jwtToken);
//            Cache.ValueWrapper wrapper = this.cache.get(cacheKey);
//            if (wrapper != null) {
//                UserEntity loginUser = this.cache.get(cacheKey, UserEntity.class);
//                if (jwtTokenUtil.validateToken(jwtToken, loginUser)) {
////                    UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
////                            loginUser, null, loginUser.getAuthorities());
////                    if (logger.isDebugEnabled()) {
////                        logger.debug("已授权用户 '{}', 设置 Security Context", username);
////                    }
////                    SecurityContextHolder.getContext().setAuthentication(authentication);
//                }
//            } else {
//                tokenExpire(response,"使用的令牌已经过期。");
//            }
//        }
//
//        filterChain.doFilter(request, response);
//    }
//
//    private void tokenExpire(HttpServletResponse httpServletResponse, String message) throws IOException {
//        Results results = Results.error(message);
//        httpServletResponse.setContentType("application/json;charset=utf-8");
//        httpServletResponse.setStatus(403);
//        httpServletResponse.getOutputStream().write(JSON.toJSONString(results).getBytes());
//    }
//
//
////    public void setAccessDeniedHandler(AccessDeniedHandler accessDeniedHandler) {
////        this.accessDeniedHandler = accessDeniedHandler;
////    }
//}
