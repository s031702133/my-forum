package com.example.forum2.service;

import com.example.forum2.dao.UserDao;
import com.example.forum2.dto.RegisterDto;
import com.example.forum2.dto.UserDto;
import com.example.forum2.mapper.UserMapper;
import com.example.forum2.pojo.User;
import com.example.forum2.pojo.UserExample;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    //注册
    public int register(RegisterDto registerDto)  {
        UserExample example = new UserExample();
        UserExample.Criteria criteria = example.createCriteria();
        criteria.andUserNameEqualTo(registerDto.getUsername());
        List<User> userList = userMapper.selectByExample(example);
        if(userList.size() != 0){
            return 0;
        }
        else {
            User user = new User();
            user.setUserName(registerDto.getUsername());
            user.setPassword(registerDto.getPassword());
            user.setEmail(registerDto.getEmail());
            user.setCreateTime(new Date());
            userMapper.insertSelective(user);
            return 1;
        }
    }

    public List<User> sjh(String name) {
        UserExample userExample = new UserExample();
        UserExample.Criteria userExampleCriteria = userExample.createCriteria();
        userExampleCriteria.andUserNameEqualTo(name);
        List<User> usersList = userMapper.selectByExample(userExample);
        return usersList;
//        User user = new User();
//        user.setUserName(name);
//        JSONObject res = new JSONObject();
//        List<User> users = userMapper.sjh(user);
//        res.put("data",users);
//        return res;
    }

    public Object login(User user) {
        UserExample example = new UserExample();
        UserExample.Criteria criteria = example.createCriteria();
        criteria.andPasswordEqualTo(user.getPassword());
        criteria.andUserNameEqualTo(user.getUserName());
        List<User> userList = userMapper.selectByExample(example);
        JSONObject res = new JSONObject();
        if(null == userList || userList.size() == 0) {
            res.put("error", "用户名或密码错误！");
            return res;
        }
        else {
            res.put("data",userList);
            return res;
        }
    }

    public Object getUser(User user) {
        return userMapper.selectByPrimaryKey(user.getUserId());
    }
}
