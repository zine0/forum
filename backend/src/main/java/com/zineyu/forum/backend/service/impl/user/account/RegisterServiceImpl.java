package com.zineyu.forum.backend.service.impl.user.account;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zineyu.forum.backend.mapper.UserMapper;
import com.zineyu.forum.backend.pojo.User;
import com.zineyu.forum.backend.service.user.account.RegisterService;
import com.zineyu.forum.backend.service.utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class RegisterServiceImpl implements RegisterService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Map<String, String> register(String username, String password, String confirmPassword, String email) {

        Map<String,String> response = new HashMap<>();

        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", username);
        User user = userMapper.selectOne(queryWrapper);
        if (user != null) {
            response.put("message", "用户名重复");
            return response;
        }
        if( username == null || password == null || confirmPassword == null || email == null ) {
            response.put("message", "缺少必要信息");
            return response;
        }
        if(username.length() > 20){
            response.put("message", "用户名过长");
            return response;
        }
        if(password.length() > 50){
            response.put("message", "密码过长");
            return response;
        }
        if(!password.equals(confirmPassword)){

            response.put("message", "两次的密码不相同");
            return response;
        }
        String encodePassword = passwordEncoder.encode(password);

        user = new User();
        user.setUsername(username);
        user.setPassword(encodePassword);
        user.setEmail(email);
        userMapper.insert(user);
        response.put("message", "success");

        String jwt = utils.JwtUtil.createJWT(user.getId().toString());
        response.put("token", jwt);

        return response;

    }
}
