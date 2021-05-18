package com.example.forum2.controller;

import com.example.forum2.dto.CommentDto;
import com.example.forum2.pojo.Comment;
import com.example.forum2.pojo.Topic;
import com.example.forum2.service.CommentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.sf.jsqlparser.statement.select.Top;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "评论")
@RestController
public class CommentController {
    @Autowired
    private CommentService commentService;

    @ApiOperation(value = "获取评论", httpMethod = "POST")
    @PostMapping(value = "/comment/getAllComment")
    public Object getAllComment(@RequestBody CommentDto commentDto) {
        JSONObject res = new JSONObject();
        res.put("comment",commentService.getAllComment(commentDto));
        res.put("count",commentService.getCommentCount(commentDto));
        return res;
    }

    @ApiOperation(value = "获取评论数量", httpMethod = "POST")
    @PostMapping(value = "/comment/getCommentCount")
    public Object getCommentCount(@RequestBody CommentDto commentDto) {
        JSONObject res = new JSONObject();
        res.put("count",commentService.getCommentCount(commentDto));
        return res;
    }

    @ApiOperation(value = "回复", httpMethod = "POST")
    @PostMapping(value = "/comment/addComment")
    public Object addTopic(@RequestBody Comment comment) {
        return commentService.addComment(comment);
    }
}
