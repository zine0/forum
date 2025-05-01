package com.zineyu.forum.backend.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zineyu.forum.backend.dto.post.GetFeaturedPostDto;
import com.zineyu.forum.backend.dto.post.PostPreviewDto;
import com.zineyu.forum.backend.pojo.FeaturedPost;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface FeaturedPostMapper extends BaseMapper<FeaturedPost> {

    List<GetFeaturedPostDto> selectFeaturedPost();
}
