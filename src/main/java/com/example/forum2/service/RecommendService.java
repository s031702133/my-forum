package com.example.forum2.service;

import com.example.forum2.mapper.LikeMapper;
import com.example.forum2.mapper.TopicMapper;
import com.example.forum2.mapper.UserMapper;
import com.example.forum2.mapper.VisitMapper;
import com.example.forum2.pojo.*;
import com.example.forum2.util.LikeMap;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

import static java.lang.Math.sqrt;

@Service
public class RecommendService {
    @Autowired
    UserMapper userMapper;

    @Autowired
    VisitMapper visitMapper;

    @Autowired
    LikeMapper likeMapper;

    @Autowired
    TopicMapper topicMapper;
    //根据帖子id获取帖子
    public Object getRecommendTopic(List<Integer> topicId){
        List<Topic> topicList = new ArrayList<>();
        for(int i = 0;i < topicId.size(); i++){
            Topic topic = topicMapper.selectByPrimaryKey(topicId.get(i));
            topicList.add(topic);
        }
        return topicList;
    }

    public List<Integer> recommend(int userId){
        //获取所有用户
        UserExample example = new UserExample();
        List<User> userList = userMapper.selectByExample(example);
        System.out.println("所有用户"+userList.get(0).getUserId());
        //目标用户行为数据
        HashMap<Integer,Integer> target = likeMap(userId);

        System.out.println("目标用户行为数据"+target);
        //存放用户相似度
        HashMap<Integer,Double> userSimilar = new HashMap<>();
        //存放用户行为数据
        List<LikeMap> likeMapList = new ArrayList<>();

        for(int i = 0;i<userList.size();i++){
            LikeMap likeMap=new LikeMap();
            likeMap.setUserId(userList.get(i).getUserId());
            likeMap.setMap(likeMap(userList.get(i).getUserId()));
            likeMapList.add(likeMap);
            System.out.println("第"+i+"个用户的行为数据"+likeMap.getMap()+likeMap.getUserId());
        }

        for(int i= 0;i<likeMapList.size();i++){
            if(likeMapList.get(i).getUserId() == userId){
                System.out.println(likeMapList.get(i).getUserId()+" 相同id "+userId);
                continue;
            }
            else{
                System.out.println(likeMapList.get(i).getUserId()+ " "+ target);
                userSimilar.put(likeMapList.get(i).getUserId(),equalLike(likeMapList.get(i).getMap(),target));
                System.out.println("用户id------------"+likeMapList.get(i).getUserId()+ " 目标用户id"+userId+ " 相似度-------------"+ equalLike(likeMapList.get(i).getMap(),target));
            }
        }
        System.out.println("所有用户相似度"+userSimilar);
        int maxSimilarUserId = maxSimilar(userSimilar);
        //得到最相似用户的行为数据
        HashMap<Integer,Integer> recommendUser = likeMap(maxSimilarUserId);

        List<Integer> recommendList = recommendTopic(target,recommendUser);


//        for(Integer i=0;i<=userList.size();i++){
//            if(userId != userList.get(i).getUserId());{
//                double value = similar(userId,userList.get(i).getUserId());
//                userMap.put(userList.get(i).getUserId(),value);
//            }
//        }
            return recommendList;
    }

    public double similar(int userIdA,int userIdB){

        return 0.0;
    };
    //两个用户都看过的帖子
//    public Object visitEqualTopic(int userIdA,int userIdB){
//        HashMap<Integer,Integer> map = new HashMap<>();
//        List<Visit> visitListA = visitTopic(userIdA);
//        List<Visit> visitListB = visitTopic(userIdB);
//        for(int i = 0;i<visitListA.size();i++){
//            for(int j =0;j<visitListB.size();j++){
//                if(visitListA.get(i).getTid()==visitListB.get(j).getTid()){
//                    System.out.println(visitListA.get(i).getTid());
//                    System.out.println(visitListB.get(i).getTid());
//                    map.put(visitListA.get(i).getTid(),0);
//                }
//            }
//        }
//        System.out.println(map);
//        return map;
//    }
    public List<Integer> recommendTopic(HashMap<Integer,Integer> target,HashMap<Integer,Integer> recommend){
        List<Integer> recommendList = new ArrayList<>();
        Set targetSet = new HashSet(0);
        Set recommendSet = new HashSet(0);
//        set.addAll(map.keySet());
        targetSet.addAll(target.keySet());
        recommendSet.addAll(recommend.keySet());
        target.keySet().removeAll(recommendSet);
        recommend.keySet().removeAll(targetSet);
//        for(int keyR:recommend.keySet()){
//            for(int keyT:target.keySet()){
//                if(keyT == keyR){
//                    recommend.remove(keyR);
//                    target.remove(keyT);
//                }
//            }
//        }
        for(int key:recommend.keySet()){
            if(recommend.get(key) == 1){
                recommendList.add(key);
                if(recommendList.size() >= 5){
                    break;
                }
            }
        }
        return recommendList;
    }
    //找出最相似度最高的用户，返回用户id
    public int maxSimilar(HashMap<Integer,Double> map){
        double max = 0.0;
        int userId = 0;
        for(Integer key:map.keySet()){
            if(map.get(key) > max){
                max = map.get(key);
                userId = key;
            }
            else{
                continue;
            }
        }
        System.out.println("最相似用户id：   "+ userId);
      return userId;
    };

    public double test(Integer uid1,Integer uid2){
        equalLike(likeMap(uid1),likeMap(uid2));
        return 0.0;
    }
    //一个用户看过的帖子
    public List<Visit> visitTopic(Integer userId) {
        VisitExample example = new VisitExample();
        VisitExample.Criteria criteria = example.createCriteria();
        criteria.andUidEqualTo(userId);
        return visitMapper.selectByExample(example);
    }
    //一个用户喜欢的帖子
    public List<Like> likeTopic(Integer userId) {
        LikeExample example = new LikeExample();
        LikeExample.Criteria criteria = example.createCriteria();
        criteria.andUidEqualTo(userId);
        return likeMapper.selectByExample(example);
    }
    // 用户阅读过的帖子里，喜欢的为1
    public HashMap<Integer, Integer> likeMap(Integer userId){
        HashMap<Integer,Integer> map = new HashMap<>();
        List<Visit> visitList = visitTopic(userId);
        List<Like> likeList = likeTopic(userId);
        for(int i = 0;i<visitList.size();i++){
            map.put(visitList.get(i).getTid(),0);
        }
        for(int i = 0;i<likeList.size();i++){
            map.put(likeList.get(i).getTid(),1);
        }
        return map;
    }
    //从两个用户中找出都阅读过的帖子，再计算相似度
    public double equalLike(HashMap<Integer,Integer> aMap,HashMap<Integer,Integer> bMap){
        HashMap<Integer,Integer> equalaMap = new HashMap<>();
        HashMap<Integer,Integer> equalbMap = new HashMap<>();

        for(Integer keyA:aMap.keySet()){
            for(Integer keyB:bMap.keySet()){
                if(keyA == keyB){
                    equalaMap.put(keyA,aMap.get(keyA));
                    equalbMap.put(keyB,bMap.get(keyB));
                }
            }
        }
        System.out.println(equalaMap + " " + equalbMap);

        return cosine(equalaMap,equalbMap);
    }

    public Double cosine(Map<Integer,Integer> aMap, Map<Integer,Integer> bMap) {
        int A = 0;
        int B = 0;
        int C = 0;
        double num = 0;
        //遍历
        Set<Integer> set = aMap.keySet();
        for (Integer key : set) {
            A += aMap.get(key) * bMap.get(key);
        }
        for (Integer key : set) {
            B += aMap.get(key) * aMap.get((key));
        }
        for (Integer key : set) {
            C += bMap.get(key) * bMap.get((key));
        }
//        System.out.println(A + "  " + B + "  " + C);
        if (B * C == 0) {
            return 0.0;
        } else {
            num = A / (sqrt(B) * sqrt(C));
//            System.out.println("相似度"+num);
            return num;
        }
    }

}
