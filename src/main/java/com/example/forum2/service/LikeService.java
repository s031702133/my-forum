package com.example.forum2.service;

import com.example.forum2.dto.TopicDto;
import com.example.forum2.mapper.LikeMapper;
import com.example.forum2.pojo.Like;
import com.example.forum2.pojo.LikeExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class LikeService {

    @Autowired
    LikeMapper likeMapper;
    //查找用户是否喜欢该帖子
    public Boolean getLike(TopicDto topicDto){
        LikeExample example = new LikeExample();
        LikeExample.Criteria criteria = example.createCriteria();
        criteria.andUidEqualTo(topicDto.getUid());
        criteria.andTidEqualTo(topicDto.getTid());
        if(likeMapper.selectByExample(example).size()==0){
            //true表示用户已经喜欢该帖子
            return false;
        }
        else {
            return true;
        }
    }
    //喜欢一个帖子
    public Boolean likeTopic(TopicDto topicDto){
        Like _like = new Like();
        _like.setTid(topicDto.getTid());
        _like.setUid(topicDto.getUid());
        _like.setCreateTime(new Date());
        likeMapper.insertSelective(_like);
        return true;
    }
    //不喜欢一个帖子
    public Boolean dislikeTopic(TopicDto topicDto){
        LikeExample example = new LikeExample();
        LikeExample.Criteria criteria = example.createCriteria();
        criteria.andTidEqualTo(topicDto.getTid());
        criteria.andUidEqualTo(topicDto.getUid());
        likeMapper.deleteByExample(example);
        return false;
    }
}
