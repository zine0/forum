package com.zineyu.forum.backend.controller.user.post;

import com.zineyu.forum.backend.service.user.post.QueryLikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class QueryLikeController {

    @Autowired
    QueryLikeService queryLikeService;

    @GetMapping("/user/post/like")
    public Map<String, Boolean> isLike(@RequestParam int postId)
    {
        return queryLikeService.isLike(postId);
    }

}
