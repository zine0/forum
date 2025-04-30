package com.zineyu.forum.backend.service.impl.user.post;

import com.zineyu.forum.backend.mapper.PostMapper;
import com.zineyu.forum.backend.pojo.Post;
import com.zineyu.forum.backend.pojo.User;
import com.zineyu.forum.backend.service.impl.utils.UserDetailsImpl;
import com.zineyu.forum.backend.service.user.post.DelUserPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class DelUserPostServiceImpl implements DelUserPostService {

    @Autowired
    PostMapper postMapper;

    @Override
    public Map<String, String> delUserPost(int id) {
        Map<String, String> map = new HashMap<String, String>();
        UsernamePasswordAuthenticationToken token = (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl userDetails = (UserDetailsImpl) token.getPrincipal();
        User user = userDetails.getUser();
        Post post = new Post();
        post = postMapper.selectById(id);

        if(post == null) {
            map.put("mseeage", "no such post");
            return map;
        }

        if(post.getAuthorId() != user.getId()) {
            map.put("message", "that not you post");
            return map;
        }

        postMapper.deleteById(id);
        map.put("message", "success");
        return map;

    }
}
