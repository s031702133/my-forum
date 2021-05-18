package com.example.forum2.controller;

import com.example.forum2.dto.TopicDto;
import com.example.forum2.pojo.Topic;
import com.example.forum2.service.RecommendService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api(tags = "推荐")
@RestController
public class RecommendController {

    @Autowired
    private RecommendService recommendService;

    @ApiOperation(value = "推荐", httpMethod = "POST")
    @PostMapping(value = "/recommend")
    public Object recommend(@RequestBody TopicDto topicDto) {
        List<Integer> list = recommendService.recommend(topicDto.getUid());
        return recommendService.getRecommendTopic(list);
    }
}
