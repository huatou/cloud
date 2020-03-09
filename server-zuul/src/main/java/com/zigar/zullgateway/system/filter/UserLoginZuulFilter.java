//package com.zigar.zullgateway.system.filter;
//
//import com.fasterxml.jackson.core.JsonProcessingException;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.netflix.zuul.ZuulFilter;
//import com.netflix.zuul.context.RequestContext;
//import com.zigar.api.entity.model.Results;
//import org.apache.commons.lang.StringUtils;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
//import javax.servlet.http.HttpServletRequest;
//
///**
// * 网关过滤器
// * 加入到Spring容器
// * <p>
// * 方法执行顺序
// * -filterType
// * -shouldFilter
// * -run
// * -filterType
// *
// * @author Zigar
// */
//@Component
//public class UserLoginZuulFilter extends ZuulFilter {
//
//    @Autowired
//    ObjectMapper objectMapper;
//
//    @Override
//    public boolean shouldFilter() {
//        return true; // 该过滤器需要执行
//    }
//
//    @Override
//    public Object run() { //编写业务逻辑
//        RequestContext requestContext = RequestContext.getCurrentContext();
//        HttpServletRequest request = requestContext.getRequest();
//        String token = request.getParameter("token");
//        if (StringUtils.isEmpty(token)) {
//            requestContext.setSendZuulResponse(false); // 过滤该请求，不对其进行路由
//            requestContext.setResponseStatusCode(401); // 设置响应状态码
//            requestContext.getResponse().setContentType("application/json;charset=UTF-8");//设置编码格式
//            String result = "";
//            try {
//                result = objectMapper.writeValueAsString(Results.error("token不能为空"));
//            } catch (JsonProcessingException e) {
//                e.printStackTrace();
//            }
//            requestContext.setResponseBody(result); // 设置响应状态码
//            return null;
//        }
//        return null;
//    }
//
//    @Override
//    public String filterType() {
//        return ZuulFilterType.PRE; // 设置过滤器类型为：pre
//    }
//
//    @Override
//    public int filterOrder() {
//        return 0;// 设置执行顺序为0
//    }
//}