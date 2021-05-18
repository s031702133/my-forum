package com.example.forum2.controller;

import com.example.forum2.dto.TopicDto;
import com.example.forum2.service.LikeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "喜欢")
@RestController
public class LikeController {
    @Autowired
    private LikeService likeService;

    @ApiOperation(value = "判断用户是否喜欢该帖子", httpMethod = "POST")
    @PostMapping(value = "/like/likeOrNot")
    public Boolean likeOrNot(@RequestBody TopicDto topicdto) {
        return likeService.getLike(topicdto);
    }

    @ApiOperation(value = "喜欢一个帖子", httpMethod = "POST")
    @PostMapping(value = "/like/likeTopic")
    public Boolean likeTopic(@RequestBody TopicDto topicdto) {
        return likeService.likeTopic(topicdto);
    }

    @ApiOperation(value = "不喜欢一个帖子", httpMethod = "POST")
    @PostMapping(value = "/like/dislikeTopic")
    public Boolean dislikeTopic(@RequestBody TopicDto topicdto) {
        return likeService.dislikeTopic(topicdto);
    }


}
