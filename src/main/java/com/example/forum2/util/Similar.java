package com.example.forum2.util;

import com.example.forum2.pojo.User;
import com.example.forum2.pojo.UserExample;
import org.springframework.beans.factory.annotation.Autowired;

import java.lang.reflect.Array;
import java.util.*;

import static java.lang.Math.sqrt;


public class Similar {
    //余弦相似度计算用户的相似度 范围0-1,越接近1越相似
    public Double cosine(Map<Integer,Integer> aMap,Map<Integer,Integer> bMap) {
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
        System.out.println(A+"  "+B+"  "+C);
        if(B*C == 0){
            return 0.0;
        }
        else{
            num = A/(sqrt(B) * sqrt(C));
            System.out.println(num);
            return num;
        }

    };

//    public void recommend(Integer userid) {
//        UserExample example = new UserExample();
//        List<User> userList =
//    }
}
