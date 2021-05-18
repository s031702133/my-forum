package com.example.forum2.dao;

import com.example.forum2.dto.RegisterDto;
import com.example.forum2.mapper.UserMapper;
import com.example.forum2.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestBody;

@Repository
public class UserDao {

    @Autowired
    private UserMapper userMapper;

    public int insertSelective(User user){
        return userMapper.insertSelective(user);
    }
}
