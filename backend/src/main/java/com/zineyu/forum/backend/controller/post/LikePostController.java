package com.zineyu.forum.backend.controller.post;

import com.zineyu.forum.backend.service.post.LikePostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class LikePostController {

    @Autowired
    private LikePostService likePostService;

    @GetMapping("/post/like")
    public Map<String,String> likePost(@RequestParam int postId) {
        return likePostService.likePost(postId);
    }
}
