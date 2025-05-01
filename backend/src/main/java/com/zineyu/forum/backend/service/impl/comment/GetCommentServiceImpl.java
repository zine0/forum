package com.zineyu.forum.backend.service.impl.comment;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zineyu.forum.backend.dto.comment.GetCommentDto;
import com.zineyu.forum.backend.mapper.CommentMapper;
import com.zineyu.forum.backend.service.comment.GetCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GetCommentServiceImpl implements GetCommentService {

    @Autowired
    private CommentMapper commentMapper;

    @Override
    public Page<GetCommentDto> getComments(Integer page, Integer size, Integer postId) {
        Page<GetCommentDto> pageParm = new Page<>(page, size);
        Page<GetCommentDto> pageDto = commentMapper.selectPostCommentPage(pageParm,postId);
        return pageDto;
    }
}
