package com.example.forum2.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "测试用接口")
@RestController
public class TestController {

    @ApiOperation(value = "基础测试1", httpMethod = "POST")
    @PostMapping(value = "/test/test1")
    public Object listUserCards(@RequestBody int testNumber) {
        return testNumber*2;
    }
}
