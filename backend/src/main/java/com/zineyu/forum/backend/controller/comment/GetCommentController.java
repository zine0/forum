package com.zineyu.forum.backend.controller.comment;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zineyu.forum.backend.dto.comment.GetCommentDto;
import com.zineyu.forum.backend.service.comment.GetCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GetCommentController {

    @Autowired
    GetCommentService getCommentService;

    @GetMapping("/comment")
    Page<GetCommentDto> getComment(@RequestParam int page, @RequestParam int size, @RequestParam int postId) {
        return getCommentService.getComments(page, size, postId);
    }
}
