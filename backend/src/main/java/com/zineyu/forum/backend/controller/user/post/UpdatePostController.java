package com.zineyu.forum.backend.controller.user.post;

import com.zineyu.forum.backend.dto.post.PostCreateDto;
import com.zineyu.forum.backend.service.user.post.UpdatePostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class UpdatePostController {

    @Autowired
    UpdatePostService updatePostService;

    @PostMapping("/user/post/update")
    public Map<String, String> updatePost(@RequestBody PostCreateDto postCreateDto) {
        return updatePostService.updatePost(postCreateDto);
    }
}
