package com.zineyu.forum.backend.mapper;

import com.zineyu.forum.backend.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class UserMapperTest {
    @Autowired
    private UserMapper userMapper;

    @Test
    public void testInsert() {
        User user = new User();
        user.setUsername("zineyu");
        user.setPassword("123456");
        user.setEmail("zine_yu@outlook.com");
        userMapper.insert(user);
    }

    @Test
    public void testSelect() {
        System.out.println("=== Test Select ===");
        List<User> userList = userMapper.selectList(null);
        for (User user : userList) {
            System.out.println(user);
        }
    }
}
