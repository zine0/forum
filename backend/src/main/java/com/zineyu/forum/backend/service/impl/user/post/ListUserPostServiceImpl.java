package com.zineyu.forum.backend.service.impl.user.post;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zineyu.forum.backend.dto.post.UserPostPreviewDto;
import com.zineyu.forum.backend.dto.user.UserPostPageDto;
import com.zineyu.forum.backend.mapper.PostMapper;
import com.zineyu.forum.backend.pojo.Post;
import com.zineyu.forum.backend.pojo.User;
import com.zineyu.forum.backend.service.impl.utils.UserDetailsImpl;
import com.zineyu.forum.backend.service.user.post.ListUserPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ListUserPostServiceImpl implements ListUserPostService {

    @Autowired
    PostMapper postMapper;

    @Override
    public UserPostPageDto listUserPost(int page, int size) {
        // 获取当前登录用户
        UsernamePasswordAuthenticationToken token = (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl userDetails = (UserDetailsImpl) token.getPrincipal();
        User user = userDetails.getUser();

        // 查询条件：只查询当前用户的文章
        QueryWrapper<Post> wrapper = new QueryWrapper<>();
        wrapper.eq("author_id", user.getId());

        // 分页查询
        Page<Post> pageParam = new Page<>(page, size);
        Page<Post> resultPage = postMapper.selectPage(pageParam, wrapper);

        // 将 Post 转换为只包含必要字段的 DTO
        List<UserPostPreviewDto> simplePosts = resultPage.getRecords().stream()
                .map(post -> new UserPostPreviewDto(
                        post.getId(),
                        post.getTitle(),
                        post.getPreview(),
                        post.getAuthorId(),
                        post.getLikes()
                ))
                .collect(Collectors.toList());

        // 构建返回结果
        UserPostPageDto pageDto = new UserPostPageDto();
        pageDto.setPosts(simplePosts);        // 使用精简的 DTO 列表
        pageDto.setTotalPages(resultPage.getPages());
        pageDto.setHasNext(resultPage.hasNext());
        pageDto.setHasPrevious(resultPage.hasPrevious());

        return pageDto;
    }

}
