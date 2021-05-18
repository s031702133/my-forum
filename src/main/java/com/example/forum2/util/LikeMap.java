package com.example.forum2.util;

import io.swagger.models.auth.In;

import java.util.HashMap;

public class LikeMap {
    private Integer userId;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    HashMap<Integer,Integer> map = new HashMap<>();

    public HashMap<Integer, Integer> getMap() {
        return map;
    }

    public void setMap(HashMap<Integer, Integer> map) {
        this.map = map;
    }
}
