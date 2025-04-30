package com.zineyu.forum.backend.service.impl.post;

import com.zineyu.forum.backend.dto.post.PosPreviewtDto;
import com.zineyu.forum.backend.dto.post.PostCreateDto;
import com.zineyu.forum.backend.mapper.PostMapper;
import com.zineyu.forum.backend.pojo.Post;
import com.zineyu.forum.backend.service.post.UpdatePostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class UpdatePostServiceImpl implements UpdatePostService {

    @Autowired
    PostMapper postMapper;

    @Override
    public Map<String, String> updatePost(PostCreateDto postCreateDto) {
        Map<String, String> map = new HashMap<>();

        Post post = new Post();
        post = postMapper.selectById(postCreateDto.getId());
        if(post == null) {
            map.put("message","no such post");
            return map;
        }

        if(!(postCreateDto.getTitle() == null || postCreateDto.getTitle().isEmpty())) {
            post.setTitle(postCreateDto.getTitle());
        }

        if(!(postCreateDto.getContent() == null || postCreateDto.getContent().isEmpty())) {
            post.setContent(postCreateDto.getContent());
        }

        postMapper.updateById(post);
        map.put("message", "success");
        return map;
    }
}
