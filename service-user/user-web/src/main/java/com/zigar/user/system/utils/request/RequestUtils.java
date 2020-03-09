package com.zigar.user.system.utils.request;


import com.zigar.api.entity.UserEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;


/**
 * 请求处理的工具类
 * Created by yangzihua on 2019/5/5.
 */
public class RequestUtils {


    private static final String CURRENT_USER_NAME = "current_user";


    public static final String AJAX_HEADER = "X-Requested-With";
    public static final String AJAX_TAG = "XMLHttpRequest";

//    public static final boolean isAjax(HttpServletRequest request) {
//        String requestType = request.getHeader(AJAX_HEADER);
//        return StringUtils.equals(requestType, AJAX_TAG);
//    }
//
//    public static void saveRequest(HttpServletRequest request) {
//        System.out.println("浏览器基本信息：" + request.getHeader("user-agent"));
//        System.out.println("客户端系统名称：" + System.getProperty("os.name"));
//        System.out.println("客户端系统版本：" + System.getProperty("os.version"));
//        System.out.println("客户端操作系统位数：" + System.getProperty("os.arch"));
//        System.out.println("HTTP协议版本：" + request.getProtocol());
//        System.out.println("请求编码格式：" + request.getCharacterEncoding());
//        System.out.println("Accept：" + request.getHeader("Accept"));
//        System.out.println("Accept-语言：" + request.getHeader("Accept-Language"));
//        System.out.println("Accept-编码：" + request.getHeader("Accept-Encoding"));
//        System.out.println("Connection：" + request.getHeader("Connection"));
//        System.out.println("Cookie：" + request.getHeader("Cookie"));
//        System.out.println("客户端发出请求时的完整URL" + request.getRequestURL());
//        System.out.println("请求行中的资源名部分" + request.getRequestURI());
//        System.out.println("请求行中的参数部分" + request.getRemoteAddr());
//        System.out.println("客户机所使用的网络端口号" + request.getRemotePort());
//        System.out.println("WEB服务器的IP地址" + request.getLocalAddr());
//        System.out.println("WEB服务器的主机名" + request.getLocalName());
//        System.out.println("客户机请求方式" + request.getMethod());
//        System.out.println("请求的文件的路径" + request.getServerName());
//        try {
//            System.out.println("请求体的数据流" + request.getReader());
//            BufferedReader br = request.getReader();
//            String res = "";
//            while ((res = br.readLine()) != null) {
//                System.out.println("request body:" + res);
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        System.out.println("请求所使用的协议名称" + request.getProtocol());
//        System.out.println("请求中所有参数的名字" + request.getParameterNames());
//        Enumeration enumNames = request.getParameterNames();
//        while (enumNames.hasMoreElements()) {
//            String key = (String) enumNames.nextElement();
//            System.out.println("参数名称：" + key);
//        }
//    }


    public static final void setCurrentUser(UserEntity loginUser) {
        Authentication authentication = new UsernamePasswordAuthenticationToken(loginUser, loginUser.getPassword());
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    public static final void clearCurrentUser() {
        SecurityContextHolder.getContext().setAuthentication(null);
    }


    public static final UserEntity getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            Object principal = authentication.getPrincipal();
            if (principal != null && principal instanceof UserEntity) {
                UserEntity userEntity = (UserEntity) principal;
                return userEntity;
            }
        }
        return null;
    }

}
