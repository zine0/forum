package com.zineyu.forum.backend.service.post;

import com.zineyu.forum.backend.dto.post.GetFeaturedPostDto;

import java.util.List;

public interface GetFeaturedPostService {
    List<GetFeaturedPostDto> getFeaturedPosts();
}
