package com.zineyu.forum.backend.service.impl.comment;

import com.zineyu.forum.backend.mapper.CommentMapper;
import com.zineyu.forum.backend.mapper.PostMapper;
import com.zineyu.forum.backend.mapper.UserMapper;
import com.zineyu.forum.backend.pojo.Comment;
import com.zineyu.forum.backend.pojo.Post;
import com.zineyu.forum.backend.pojo.User;
import com.zineyu.forum.backend.service.comment.CreateCommentService;
import com.zineyu.forum.backend.service.impl.utils.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class CreateCommentServiceImpl implements CreateCommentService {

    @Autowired
    CommentMapper commentMapper;

    @Autowired
    PostMapper postMapper;

    @Override
    public Map<String, String> createComment(Integer postId, String content) {
        UsernamePasswordAuthenticationToken token = (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl userDetails = (UserDetailsImpl) token.getPrincipal();
        User user = userDetails.getUser();

        Map<String, String> result = new HashMap<>();

        Post post = postMapper.selectById(postId);
        if(post == null) {
            result.put("message", "no post found");
            return result;
        }

        Comment comment = new Comment();
        comment.setPostId(postId);
        comment.setContent(content);
        comment.setAuthorId(user.getId());

        commentMapper.insert(comment);

        post.setComments(post.getComments() + 1);
        postMapper.updateById(post);

        result.put("message", "success");
        return result;
    }
}
