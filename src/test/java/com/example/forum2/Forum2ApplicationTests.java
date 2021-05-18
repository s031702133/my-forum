package com.example.forum2;

import com.example.forum2.dto.RegisterDto;
import com.example.forum2.pojo.User;
import com.example.forum2.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class Forum2ApplicationTests {

    @Autowired
    private UserService us;
    @Test
    void contextLoads() {
//        RegisterDto user = new RegisterDto();
//        user.setUsername("sjh");
//        user.setPassword("123");
//        us.register(user);
    }

}
