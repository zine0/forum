package com.zineyu.forum.backend.service.comment;

import java.util.Map;

public interface CreateCommentService {

    public Map<String,String> createComment(Integer postId,String content);

}
