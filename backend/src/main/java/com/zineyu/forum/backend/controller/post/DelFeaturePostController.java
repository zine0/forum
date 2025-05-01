package com.zineyu.forum.backend.controller.post;

import com.zineyu.forum.backend.service.post.DelFeaturePostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class DelFeaturePostController {

    @Autowired
    DelFeaturePostService delFeaturePostService;

    @DeleteMapping("/post/feature")
    public Map<String,String> delFeature(@RequestParam int postId) {
        return delFeaturePostService.delFeaturePost(postId);
    }
}
