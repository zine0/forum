package com.zineyu.forum.backend.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zineyu.forum.backend.dto.post.PosPreviewtDto;
import com.zineyu.forum.backend.pojo.Post;
import org.apache.ibatis.annotations.Param;

public interface PostMapper extends BaseMapper<Post> {
    Page<PosPreviewtDto> selectPostPage(@Param("page") Page<PosPreviewtDto> page);
}
