package com.example.forum2.service;

import com.example.forum2.dto.TopicDto;
import com.example.forum2.mapper.*;
import com.example.forum2.pojo.*;
import com.github.pagehelper.PageHelper;
import io.swagger.models.auth.In;
import net.sf.jsqlparser.statement.select.Top;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class TopicService {

    @Autowired
    private TopicMapper topicMapper;

    @Autowired
    private VisitMapper visitMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    LikeMapper likeMapper;

    @Autowired
    CollectMapper collectMapper;

    //获取帖子
    public Object getAllTopics(int pageNum,String keyWord,String tags){
        TopicExample example = new TopicExample();
        example.setOrderByClause("create_time DESC");
        TopicExample.Criteria criteria = example.createCriteria();
        criteria.andTitleLike("%"+keyWord+"%");

            criteria.andTagsLike("%" + tags + "%");

        PageHelper.startPage(pageNum,5);
        List<Topic> topicList = topicMapper.selectByExample(example);
        return topicList;
    }
    //获取帖子数量
    public long getTopicCount(String keyWord,String tags){
        TopicExample example = new TopicExample();
        TopicExample.Criteria criteria = example.createCriteria();
        criteria.andTitleLike("%"+keyWord+"%");
            criteria.andTagsLike("%" + tags + "%");
        return topicMapper.countByExample(example);
    }
    //获取热门的帖子
    public Object getHotTopic(){
        TopicExample example = new TopicExample();
        TopicExample.Criteria criteria = example.createCriteria();
        example.setOrderByClause("visit_count DESC");
        PageHelper.startPage(1,5);
        List<Topic> topicList = topicMapper.selectByExample(example);
        return topicList;
    }
    //获取收藏的帖子
    public Object getCollectTopic(int userId) {
        CollectExample collectExample = new CollectExample();
        CollectExample.Criteria collectExampleCriteria = collectExample.createCriteria();
        collectExampleCriteria.andUidEqualTo(userId);
        List<Collect> collectList = collectMapper.selectByExample(collectExample);
        System.out.println(collectList);
//        HashMap<Integer,Topic> map = new HashMap<>();
        List<Topic> topicList = new ArrayList<>();
        for(Collect value:collectList){
//            System.out.println(value.getTid());
           Topic topic = topicMapper.selectByPrimaryKey(value.getTid());
//            System.out.println(topic);
//           map.put(topic.getTopicId(),topic);
            topicList.add(topic);
        }
        return topicList;
    }
    public Object getTopic(int topicId) {
        Topic topic = topicMapper.selectByPrimaryKey(topicId);
        topic.setVisitCount(topic.getVisitCount()+1);
        topicMapper.updateByPrimaryKeySelective(topic);
        return topic;
    }
    //获取用户发布的帖子
    public Object getTopicByUserId(int userId) {
        TopicExample example = new TopicExample();
        example.setOrderByClause("create_time DESC");
        TopicExample.Criteria criteria = example.createCriteria();
        criteria.andUidEqualTo(userId);
        PageHelper.startPage(1,10);
        List<Topic> topicList = topicMapper.selectByExample(example);
        return topicList;
    }
    //新增帖子
    public Object addTopic(Topic topic) {
//        TimeZone time = TimeZone.getTimeZone("ETC/GMT-8");
//        TimeZone.setDefault(time);
//        topic.setCreateTime(new Date());
        Date date = new Date();
//        SimpleDateFormat bjSdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");     // 北京
//        bjSdf.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));  // 设置北京时区
//        System.out.println("毫秒数:" + date.getTime() + ", 北京时间:" + bjSdf.format(date));
        System.out.println(date);
        topic.setCreateTime(date);
        return topicMapper.insertSelective(topic);
    }
    //删除帖子
    public Object deleteTopic(TopicDto topicDto) {
        LikeExample likeExample = new LikeExample();
        LikeExample.Criteria likecriteria =  likeExample.createCriteria();
        likecriteria.andTidEqualTo(topicDto.getTid());
        likeMapper.deleteByExample(likeExample);

        CollectExample collectExample = new CollectExample();
        CollectExample.Criteria collectcriteria = collectExample.createCriteria();
        collectcriteria.andTidEqualTo(topicDto.getTid());
        collectMapper.deleteByExample(collectExample);

        VisitExample visitExample = new VisitExample();
        VisitExample.Criteria visitcriteria = visitExample.createCriteria();
        visitcriteria.andTidEqualTo(topicDto.getTid());
        visitMapper.deleteByExample(visitExample);

        return topicMapper.deleteByPrimaryKey(topicDto.getTid());
    }
    //阅读记录
    public Object visitTopic(TopicDto topicDto) {
        Visit visit = new Visit();
        //查询阅读记录
        VisitExample example = new VisitExample();
        VisitExample.Criteria criteria = example.createCriteria();
        criteria.andTidEqualTo(topicDto.getTid());
        criteria.andUidEqualTo(topicDto.getUid());
        List<Visit> list =visitMapper.selectByExample(example);
        //判断是否有阅读记录
        if(list.size() == 0) {
            visit.setCreateTime(new Date());
            visit.setTid(topicDto.getTid());
            visit.setUid(topicDto.getUid());
            return visitMapper.insertSelective(visit);
        }
        else {
            //更新阅读记录
            visit.setCreateTime(new Date());
            visit.setTid(topicDto.getTid());
            visit.setUid(topicDto.getUid());
            visit.setVisitId(list.get(0).getVisitId());
            return visitMapper.updateByExample(visit,example);
        }
    }



}
