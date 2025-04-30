package com.zineyu.forum.backend.service.user.post;

import com.zineyu.forum.backend.dto.post.PostCreateDto;

import java.util.Map;

public interface UpdatePostService {
    Map<String, String> updatePost(PostCreateDto postCreateDto);
}
