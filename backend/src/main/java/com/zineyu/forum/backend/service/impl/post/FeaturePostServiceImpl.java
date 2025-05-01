package com.zineyu.forum.backend.service.impl.post;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zineyu.forum.backend.mapper.FeaturedPostMapper;
import com.zineyu.forum.backend.mapper.PostMapper;
import com.zineyu.forum.backend.pojo.FeaturedPost;
import com.zineyu.forum.backend.pojo.Post;
import com.zineyu.forum.backend.pojo.User;
import com.zineyu.forum.backend.service.impl.utils.UserDetailsImpl;
import com.zineyu.forum.backend.service.post.FeaturePostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class FeaturePostServiceImpl implements FeaturePostService {

    @Autowired
    PostMapper postMapper;

    @Autowired
    FeaturedPostMapper featuredPostMapper;

    @Override
    public Map<String, String> featurePost(Integer post_id) {
        UsernamePasswordAuthenticationToken token = (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl userDetails = (UserDetailsImpl) token.getPrincipal();
        User user = userDetails.getUser();

        Map<String,String> map = new HashMap<>();
        if(user.getPermission()!=1) {
            map.put("message","permission denied");
            return map;
        }

        Post post = postMapper.selectById(post_id);
        if(post==null) {
            map.put("message","post not found");
            return map;
        }

        FeaturedPost featuredPost = new FeaturedPost();

        QueryWrapper<FeaturedPost> wrapper = new QueryWrapper<>();
        wrapper.eq("post_id",post_id);

        featuredPost = featuredPostMapper.selectOne(wrapper);
        if(featuredPost!=null) {
            map.put("message","post already featured");
            return map;
        }
        featuredPost = new FeaturedPost();
        featuredPost.setPostId(post_id);

        featuredPostMapper.insert(featuredPost);
        map.put("message","success");
        return map;
    }
}
