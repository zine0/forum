package com.zineyu.forum.backend.controller.post;

import com.zineyu.forum.backend.service.post.FeaturePostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class FeaturedPostController {
    @Autowired
    FeaturePostService featurePostService;
    @GetMapping("/post/feature")
    public Map<String,String> featuredPost(@RequestParam Integer postId) {
        return featurePostService.featurePost(postId);
    }
}
