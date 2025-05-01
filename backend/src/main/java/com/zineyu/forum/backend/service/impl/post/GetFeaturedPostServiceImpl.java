package com.zineyu.forum.backend.service.impl.post;

import com.zineyu.forum.backend.dto.post.GetFeaturedPostDto;
import com.zineyu.forum.backend.mapper.FeaturedPostMapper;
import com.zineyu.forum.backend.service.post.GetFeaturedPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GetFeaturedPostServiceImpl implements GetFeaturedPostService {

    @Autowired
    FeaturedPostMapper featuredPostMapper;

    @Override
    public List<GetFeaturedPostDto> getFeaturedPosts() {
        List<GetFeaturedPostDto> posts = new ArrayList<>();
        posts = featuredPostMapper.selectFeaturedPost();
        return posts;
    }
}
