package com.zineyu.forum.backend.controller.post;

import com.zineyu.forum.backend.dto.post.GetPostDto;
import com.zineyu.forum.backend.service.post.GetPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GetPostController {

    @Autowired
    GetPostService getPostService;

    @GetMapping("/post/{id}")
    public GetPostDto getPost(@PathVariable int id) {

        return getPostService.getPost(id);
    }
}
