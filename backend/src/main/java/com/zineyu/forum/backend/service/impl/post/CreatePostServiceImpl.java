package com.zineyu.forum.backend.service.impl.post;

import com.zineyu.forum.backend.mapper.PostMapper;
import com.zineyu.forum.backend.pojo.Post;
import com.zineyu.forum.backend.pojo.User;
import com.zineyu.forum.backend.service.impl.utils.UserDetailsImpl;
import com.zineyu.forum.backend.service.post.CreatePostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class CreatePostServiceImpl implements CreatePostService {

    @Autowired
    private PostMapper postMapper;

    @Override
    public int insert(Post post) {
        if (post.getAuthor() == null) {
            throw new IllegalArgumentException("Post author cannot be null");
        }
        post.setAuthorId(post.getAuthor().getId());
        return postMapper.insert(post);
    }

    private static String getFirstParagraph(String markdown) {
        if (markdown == null || markdown.trim().isEmpty()) {
            return "";
        }

        // 分割段落（两个以上换行符）
        String[] paragraphs = markdown.split("\\n\\s*\\n");

        // 找到第一个非空段落
        String firstParagraph = "";
        for (String para : paragraphs) {
            if (!para.trim().isEmpty()) {
                firstParagraph = para;
                break;
            }
        }
        return firstParagraph;
    }

    @Override
    public Map<String, String> createPost(String title, String content) {

        UsernamePasswordAuthenticationToken token = (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl userDetails = (UserDetailsImpl) token.getPrincipal();
        User user = userDetails.getUser();

        Post post = new Post();
        post.setTitle(title);
        post.setContent(content);
        post.setAuthor(user);
        post.setPreview(getFirstParagraph(content));


        this.insert(post);

        Map<String, String> response = new HashMap<>();
        response.put("message", "success");

        return response;
    }
}
