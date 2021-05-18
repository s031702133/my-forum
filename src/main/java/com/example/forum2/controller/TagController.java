package com.example.forum2.controller;

import com.example.forum2.dto.RegisterDto;
import com.example.forum2.dto.TopicDto;
import com.example.forum2.dto.UserDto;
import com.example.forum2.service.TagService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "标签")
@RestController
public class TagController {
    @Autowired
    private TagService tagService;

    @ApiOperation(value = "标签", httpMethod = "POST")
    @PostMapping(value = "/tag/getAllTags")
    public Object getAllTags(@RequestBody TopicDto topicDto) {
        return tagService.getAllTags();
    }

}
