package com.zineyu.forum.backend.service.impl.post;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zineyu.forum.backend.mapper.FeaturedPostMapper;
import com.zineyu.forum.backend.pojo.FeaturedPost;
import com.zineyu.forum.backend.pojo.User;
import com.zineyu.forum.backend.service.impl.utils.UserDetailsImpl;
import com.zineyu.forum.backend.service.post.DelFeaturePostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.sql.Wrapper;
import java.util.HashMap;
import java.util.Map;

@Service
public class DelFeaturePostServiceImpl implements DelFeaturePostService {

    @Autowired
    FeaturedPostMapper featuredPostMapper;

    @Override
    public Map<String, String> delFeaturePost(int postId) {
        UsernamePasswordAuthenticationToken token = (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl userDetails = (UserDetailsImpl) token.getPrincipal();
        User user = userDetails.getUser();

        Map<String, String> result = new HashMap<>();
        if(user.getPermission() !=1)
        {
            result.put("message", "permission denied");
            return result;
        }

        QueryWrapper<FeaturedPost> wrapper = new QueryWrapper<>();
        wrapper.eq("post_id", postId);
        FeaturedPost featuredPost = featuredPostMapper.selectOne(wrapper);
        if(featuredPost == null)
        {
            result.put("message", "post does not featured");
            return result;
        }

        featuredPostMapper.delete(wrapper);
        result.put("message", "success");
        return result;
    }
}
