package com.zineyu.forum.backend.controller.post;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zineyu.forum.backend.dto.post.PostPreviewDto;
import com.zineyu.forum.backend.service.post.SearchPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SearchPostController {

    @Autowired
    SearchPostService searchPostService;

    @GetMapping("/post/search")
    public Page<PostPreviewDto> searchPost(@RequestParam int page, @RequestParam int size,@RequestParam String query) {
        return searchPostService.searchPost(page, size, query);
    }

}
