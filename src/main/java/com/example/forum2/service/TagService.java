package com.example.forum2.service;

import com.example.forum2.mapper.TagMapper;
import com.example.forum2.pojo.TagExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TagService {

    @Autowired
    TagMapper tagMapper;

    public Object getAllTags(){
        TagExample example = new TagExample();
        return tagMapper.selectByExample(example);
    }

}
