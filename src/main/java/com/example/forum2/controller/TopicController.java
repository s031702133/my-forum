package com.example.forum2.controller;

import com.example.forum2.dto.RegisterDto;
import com.example.forum2.dto.TopicDto;
import com.example.forum2.pojo.Topic;
import com.example.forum2.pojo.User;
import com.example.forum2.pojo.Visit;
import com.example.forum2.service.TopicService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "帖子")
@RestController
public class TopicController {

    @Autowired
    private TopicService topicService;

    @ApiOperation(value = "获取多个帖子", httpMethod = "POST")
    @PostMapping(value = "/topic/getAllTopic")
    public Object getAllTopic(@RequestBody TopicDto topicDto) {
        JSONObject res = new JSONObject();
        res.put("topic",topicService.getAllTopics(topicDto.getPageNum(),topicDto.getKeyWord(),topicDto.getTags()));
        res.put("count",topicService.getTopicCount(topicDto.getKeyWord(),topicDto.getTags()));
        return res;
    }

    @ApiOperation(value = "获取收藏的帖子", httpMethod = "POST")
    @PostMapping(value = "/topic/getCollectTopic")
    public Object getCollectTopic(@RequestBody TopicDto topicdto) {
        return topicService.getCollectTopic(topicdto.getUid());
    }

    @ApiOperation(value = "获取一个帖子", httpMethod = "POST")
    @PostMapping(value = "/topic/getTopic")
    public Object getTopic(@RequestBody TopicDto topicdto) {
        topicService.visitTopic(topicdto);
        return topicService.getTopic(topicdto.getTid());
    }
    @ApiOperation(value = "获取热门帖子", httpMethod = "POST")
    @PostMapping(value = "/topic/getHotTopic")
    public Object getHotTopic() {
        return topicService.getHotTopic();
    }

    @ApiOperation(value = "获取用户的帖子", httpMethod = "POST")
    @PostMapping(value = "/topic/getTopicByUserId")
    public Object getTopic(@RequestBody User user) {
        return topicService.getTopicByUserId(user.getUserId());
    }

    @ApiOperation(value = "发帖", httpMethod = "POST")
    @PostMapping(value = "/topic/addTopic")
    public Object addTopic(@RequestBody Topic topic) {
        return topicService.addTopic(topic);
    }

    @ApiOperation(value = "删帖" ,httpMethod = "POST")
    @PostMapping(value = "/topic/deleteTopic")
    public Object deleteTopic(@RequestBody TopicDto topicDto) {
        return topicService.deleteTopic(topicDto);
    }


}
