package com.zineyu.forum.backend.service.post;

import com.zineyu.forum.backend.dto.post.PosPreviewtDto;
import com.zineyu.forum.backend.dto.post.PostCreateDto;

import java.util.Map;

public interface UpdatePostService {
    public Map<String,String> updatePost(PostCreateDto postCreateDto);
}
