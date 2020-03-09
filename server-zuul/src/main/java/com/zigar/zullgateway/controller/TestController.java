package com.zigar.zullgateway.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Zigar
 * @createTime 2020-03-08 14:52:19
 * @description
 */

@RestController
public class TestController {

    @GetMapping("/test")
    Object test() {
        return "test";
    }

}
