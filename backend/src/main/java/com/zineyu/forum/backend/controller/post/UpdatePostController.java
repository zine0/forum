package com.zineyu.forum.backend.controller.post;

import com.zineyu.forum.backend.dto.post.PosPreviewtDto;
import com.zineyu.forum.backend.dto.post.PostCreateDto;
import com.zineyu.forum.backend.service.post.UpdatePostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class UpdatePostController {

    @Autowired
    UpdatePostService updatePostService;

    @PostMapping("/post/update")
    public Map<String,String> updatePost(@RequestBody PostCreateDto postCreateDto) {
        return updatePostService.updatePost(postCreateDto);
    }
}
