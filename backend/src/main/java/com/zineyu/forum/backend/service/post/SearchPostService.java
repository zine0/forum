package com.zineyu.forum.backend.service.post;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zineyu.forum.backend.dto.post.PostPreviewDto;
import org.springframework.web.bind.annotation.RequestParam;

public interface SearchPostService {
    public Page<PostPreviewDto> searchPost(@RequestParam int page, @RequestParam int size, @RequestParam String query);
}
