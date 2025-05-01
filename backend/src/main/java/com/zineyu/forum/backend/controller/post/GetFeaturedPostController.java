package com.zineyu.forum.backend.controller.post;

import com.zineyu.forum.backend.dto.post.GetFeaturedPostDto;
import com.zineyu.forum.backend.pojo.Post;
import com.zineyu.forum.backend.service.post.GetFeaturedPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class GetFeaturedPostController {

    @Autowired
    GetFeaturedPostService getFeaturedPostService;

    @GetMapping("/post/featured")
    public List<GetFeaturedPostDto> getFeaturedPosts() {
        return getFeaturedPostService.getFeaturedPosts();
    }
}
