package com.zineyu.forum.backend.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zineyu.forum.backend.dto.post.PostPreviewDto;
import com.zineyu.forum.backend.pojo.Post;
import org.apache.ibatis.annotations.Param;

public interface PostMapper extends BaseMapper<Post> {
    Page<PostPreviewDto> selectPostPage(@Param("page") Page<PostPreviewDto> page);

    Page<PostPreviewDto> searchPostPage(@Param("page") Page<PostPreviewDto> page, @Param("query") String query);
}
