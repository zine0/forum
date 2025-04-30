package com.zineyu.forum.backend.controller.post;

import com.zineyu.forum.backend.dto.post.GetPostDto;
import com.zineyu.forum.backend.pojo.Post;
import com.zineyu.forum.backend.service.post.GetPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class GetPostController {

    @Autowired
    GetPostService getPostService;

    @GetMapping("/post/{id}")
    public GetPostDto getPost(@PathVariable int id) {
        System.out.println(id);
        return getPostService.getPost(id);
    }
}
