package com.example.forum2.mapper;

import com.example.forum2.pojo.Focus;
import com.example.forum2.pojo.FocusExample;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
@Mapper
public interface FocusMapper {
    long countByExample(FocusExample example);

    int deleteByExample(FocusExample example);

    int deleteByPrimaryKey(Integer focusId);

    int insert(Focus record);

    int insertSelective(Focus record);

    List<Focus> selectByExample(FocusExample example);

    Focus selectByPrimaryKey(Integer focusId);

    int updateByExampleSelective(@Param("record") Focus record, @Param("example") FocusExample example);

    int updateByExample(@Param("record") Focus record, @Param("example") FocusExample example);

    int updateByPrimaryKeySelective(Focus record);

    int updateByPrimaryKey(Focus record);
}