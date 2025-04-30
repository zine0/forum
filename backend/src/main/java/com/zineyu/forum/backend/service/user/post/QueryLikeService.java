package com.zineyu.forum.backend.service.user.post;

import java.util.Map;

public interface QueryLikeService {
    public Map<String, Boolean> isLike(int post_id);
}
