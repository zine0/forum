package com.zineyu.forum.backend.controller.comment;

import com.zineyu.forum.backend.service.comment.CreateCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class CreateCommentController {

    @Autowired
    private CreateCommentService createCommentService;

    @PostMapping("/comment")
    public Map<String, String> createComment(@RequestBody Map<String, String> map) {
        Integer postId = Integer.parseInt(map.get("postId"));
        String content = map.get("content");
        return createCommentService.createComment(postId, content);
    }

}
