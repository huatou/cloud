package com.zigar.hbase;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Zigar
 * @createTime 2020-02-28 12:38:23
 * @description
 */

@RestController
public class TestRestController {

    @GetMapping("/test")
    Object test() {
        return "test HBase";
    }

    @PostMapping("/student")
    Object insertUser(){
        return "user";
    }

}
