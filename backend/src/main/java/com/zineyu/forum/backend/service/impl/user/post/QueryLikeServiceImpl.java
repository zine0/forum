package com.zineyu.forum.backend.service.impl.user.post;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zineyu.forum.backend.mapper.PostLikeMapper;
import com.zineyu.forum.backend.pojo.PostLike;
import com.zineyu.forum.backend.pojo.User;
import com.zineyu.forum.backend.service.impl.utils.UserDetailsImpl;
import com.zineyu.forum.backend.service.user.post.QueryLikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class QueryLikeServiceImpl implements QueryLikeService {

    @Autowired
    PostLikeMapper postLikeMapper;

    @Override
    public Map<String, Boolean> isLike(int post_id) {
        UsernamePasswordAuthenticationToken token = (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl userDetails = (UserDetailsImpl) token.getPrincipal();
        User user = userDetails.getUser();

        QueryWrapper<PostLike> wrapper = new QueryWrapper<>();
        wrapper.eq("post_id", post_id);
        wrapper.eq("user_id", user.getId());

        Map<String,Boolean> map = new HashMap<>();

        PostLike postLike = postLikeMapper.selectOne(wrapper);
        if (postLike == null) {
            map.put("isLike", false);
        }else {
            map.put("isLike", true);
        }
        return map;
    }
}
