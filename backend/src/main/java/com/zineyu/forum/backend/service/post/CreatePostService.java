package com.zineyu.forum.backend.service.post;

import com.zineyu.forum.backend.pojo.Post;

import java.util.Map;

public interface CreatePostService {
    int insert(Post post);

    Map<String,String> createPost(String title, String content);
}
