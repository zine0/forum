package com.zineyu.forum.backend.service.impl.post;

import com.zineyu.forum.backend.dto.post.GetPostDto;
import com.zineyu.forum.backend.mapper.PostMapper;
import com.zineyu.forum.backend.mapper.UserMapper;
import com.zineyu.forum.backend.pojo.Post;
import com.zineyu.forum.backend.pojo.User;
import com.zineyu.forum.backend.service.post.GetPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GetPostServiceImpl implements GetPostService {

    @Autowired
    PostMapper postMapper;

    @Autowired
    UserMapper userMapper;

    @Override
    public GetPostDto getPost(int id) {
        GetPostDto dto = new GetPostDto();

        Post post = new Post();
        post = postMapper.selectById(id);
        if (post == null) {
            return null;
        }

        User Author = new User();
        Author = userMapper.selectById(post.getAuthorId());

        dto.setId(post.getId());
        dto.setTitle(post.getTitle());
        dto.setContent(post.getContent());
        dto.setAuthorAvatar(Author.getAvatar());
        dto.setAuthorName(Author.getUsername());
        dto.setLikes(post.getLikes());
        dto.setComments(post.getComments());
        return dto;
    }
}
