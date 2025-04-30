package com.zineyu.forum.backend.controller.post;

import com.zineyu.forum.backend.service.post.CreatePostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class CreatePostController {

    @Autowired
    CreatePostService createPostService;

    @PostMapping("/post/create")
    public Map<String,String> createPost(@RequestBody Map<String,String> request) {
        String title = request.get("title");
        String content = request.get("content");
        System.out.println(title);
        System.out.println(content);
        return createPostService.createPost(title,content);
    }
}
