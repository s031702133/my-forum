package com.example.forum2.service;

import com.example.forum2.dto.CommentDto;
import com.example.forum2.mapper.CommentMapper;
import com.example.forum2.mapper.TopicMapper;
import com.example.forum2.pojo.Comment;
import com.example.forum2.pojo.CommentExample;
import com.example.forum2.pojo.Topic;
import com.example.forum2.pojo.TopicExample;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class CommentService {

    @Autowired
    private CommentMapper commentMapper;

    @Autowired
    private TopicMapper topicMapper;

    public Object getAllComment(CommentDto dto) {
        CommentExample example = new CommentExample();
        example.setOrderByClause("create_time ASC");
        CommentExample.Criteria criteria = example.createCriteria();
        criteria.andTidEqualTo(dto.getTid());
        PageHelper.startPage(dto.getPageNum(),5);
        List<Comment> commentList = commentMapper.selectByExample(example);
        return commentList;
    }

    public long getCommentCount(CommentDto dto){
        CommentExample example = new CommentExample();
        CommentExample.Criteria criteria = example.createCriteria();
        criteria.andTidEqualTo(dto.getTid());
        return commentMapper.countByExample(example);
    }

    public Object addComment(Comment comment) {
        comment.setCreateTime(new Date());
        Topic topic = new Topic();
        topic = topicMapper.selectByPrimaryKey(comment.getTid());
        int reply = topic.getReply()+1;
        topic.setReply(reply);
        topicMapper.updateByPrimaryKeySelective(topic);
        return commentMapper.insertSelective(comment);
    }
}
