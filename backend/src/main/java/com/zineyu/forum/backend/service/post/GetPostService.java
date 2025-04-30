package com.zineyu.forum.backend.service.post;

import com.zineyu.forum.backend.dto.post.GetPostDto;

public interface GetPostService {
    GetPostDto getPost(int id);
}
