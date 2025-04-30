package com.zineyu.forum.backend.controller.user.post;


import com.zineyu.forum.backend.dto.user.UserPostPageDto;
import com.zineyu.forum.backend.service.user.post.ListUserPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ListUserPostController {

    @Autowired
    ListUserPostService listUserPostService;

    @GetMapping("/user/post")
    UserPostPageDto page(@RequestParam int page, @RequestParam int size) {
        return listUserPostService.listUserPost(page, size);

    }
}
