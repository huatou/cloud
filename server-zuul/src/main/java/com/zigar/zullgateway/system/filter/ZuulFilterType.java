package com.zigar.zullgateway.system.filter;

/**
 * @author Zigar
 * @createTime 2020-02-01 17:41:15
 * @description ZuulFilterType，过滤器类型，代表请求的不同阶段
 */

public class ZuulFilterType {

    public static final String PRE = "pre";
    public static final String ROUTE = "route";
    public static final String POST = "post";
    public static final String ERROR = "error";

}
