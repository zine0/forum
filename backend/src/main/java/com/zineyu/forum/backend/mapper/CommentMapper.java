package com.zineyu.forum.backend.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zineyu.forum.backend.dto.comment.GetCommentDto;
import com.zineyu.forum.backend.pojo.Comment;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CommentMapper extends BaseMapper<Comment> {
    Page<GetCommentDto> selectPostCommentPage(@Param("page") Page<GetCommentDto> page,@Param("post_id") Integer post_id);
}
