package com.zineyu.forum.backend.controller.post;

import com.zineyu.forum.backend.dto.PageDto;
import com.zineyu.forum.backend.service.post.ListPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ListPostController {

    @Autowired
    ListPostService listPostService;

    @GetMapping("/post/page")
    PageDto page(@RequestParam int page, @RequestParam int size) {
        return listPostService.listPost(page, size);
    }

}
