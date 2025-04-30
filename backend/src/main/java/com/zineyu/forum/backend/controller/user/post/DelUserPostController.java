package com.zineyu.forum.backend.controller.user.post;

import com.zineyu.forum.backend.service.user.post.DelUserPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class DelUserPostController {
    @Autowired
    DelUserPostService delUserPostService;

    @DeleteMapping("/user/post")
    public Map<String,String> deleteUserPost(@RequestParam int id) {
        return  delUserPostService.delUserPost(id);
    }
}
