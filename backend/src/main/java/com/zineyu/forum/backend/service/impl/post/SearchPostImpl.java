package com.zineyu.forum.backend.service.impl.post;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zineyu.forum.backend.dto.post.PostPreviewDto;
import com.zineyu.forum.backend.mapper.PostMapper;
import com.zineyu.forum.backend.service.post.SearchPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SearchPostImpl implements SearchPostService {

    @Autowired
    private PostMapper postMapper;

    @Override
    public Page<PostPreviewDto> searchPost(int page, int size, String query) {
        Page<PostPreviewDto> pageDto = new Page<>(page, size);
        pageDto = postMapper.searchPostPage(pageDto, query);
        return pageDto;
    }
}
