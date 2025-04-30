package com.zineyu.forum.backend.service.user.post;

import com.zineyu.forum.backend.dto.user.UserPostPageDto;

public interface ListUserPostService {

    UserPostPageDto listUserPost(int page, int size);

}
