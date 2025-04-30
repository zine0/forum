package com.zineyu.forum.backend.service.post;

import com.zineyu.forum.backend.dto.PageDto;

public interface ListPostService {
    PageDto listPost(int page,int size);
}
