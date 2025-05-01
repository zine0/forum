package com.zineyu.forum.backend.service.comment;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zineyu.forum.backend.dto.comment.GetCommentDto;

public interface GetCommentService {
    Page<GetCommentDto> getComments(Integer page, Integer size,Integer postId);
}
