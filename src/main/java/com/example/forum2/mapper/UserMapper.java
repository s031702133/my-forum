package com.example.forum2.mapper;

import com.example.forum2.pojo.User;
import com.example.forum2.pojo.UserExample;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
@Mapper
public interface UserMapper {
    long countByExample(UserExample example);

    int deleteByExample(UserExample example);

    int deleteByPrimaryKey(Integer userId);
    //新增
    int insert(User record);
    //新增判断是否为空
    int insertSelective(User record);

    List<User> selectByExample(UserExample example);

    List<User> sjh(@Param("record")User record);

    User selectByPrimaryKey(Integer userId);

    int updateByExampleSelective(@Param("record") User record, @Param("example") UserExample example);

    int updateByExample(@Param("record") User record, @Param("example") UserExample example);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
}