package com.zineyu.forum.backend.service.impl.post;

import com.zineyu.forum.backend.mapper.PostLikeMapper;
import com.zineyu.forum.backend.mapper.PostMapper;
import com.zineyu.forum.backend.pojo.Post;
import com.zineyu.forum.backend.pojo.PostLike;
import com.zineyu.forum.backend.pojo.User;
import com.zineyu.forum.backend.service.impl.utils.UserDetailsImpl;
import com.zineyu.forum.backend.service.post.LikePostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class LikePostServiceImpl implements LikePostService {

    @Autowired
    PostLikeMapper postLikeMapper;

    @Autowired
    PostMapper postMapper;

    @Override
    public Map<String, String> likePost(int post_id) {
        UsernamePasswordAuthenticationToken token = (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl userDetails = (UserDetailsImpl) token.getPrincipal();
        User user = userDetails.getUser();

        PostLike postLike = new PostLike();
        postLike.setUserId(user.getId());
        postLike.setPostId(post_id);
        Map<String, String> result = new HashMap<>();

        Post post = new Post();
        post = postMapper.selectById(post_id);
        if (post == null) {
            result.put("message", "no such post");
            return result;
        }

        try {
            postLikeMapper.insert(postLike);
        }
        catch (Exception e) {
            e.printStackTrace();
            result.put("message", "error");
            return result;
        }
        result.put("message", "success");
        return result;
    }
}
