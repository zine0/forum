package com.zineyu.forum.backend.service.impl.post;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zineyu.forum.backend.dto.PageDto;
import com.zineyu.forum.backend.dto.post.PosPreviewtDto;
import com.zineyu.forum.backend.mapper.PostMapper;
import com.zineyu.forum.backend.service.post.ListPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ListPostServiceImpl implements ListPostService {

    @Autowired
    private PostMapper postMapper;

    @Override
    public PageDto listPost(int page, int size) {
        Page<PosPreviewtDto> pageParam = new Page<>(page, size);
        Page<PosPreviewtDto> resultPage = postMapper.selectPostPage(pageParam);

        // 构建返回DTO
        PageDto pageDto = new PageDto();
        pageDto.setPosts(resultPage.getRecords());       // 当前页数据列表
        pageDto.setTotalPages(resultPage.getPages());    // 总页数
        pageDto.setHasNext(resultPage.hasNext());        // 是否有下一页
        pageDto.setHasPrevious(resultPage.hasPrevious());// 是否有上一页

        return pageDto;

    }
}
