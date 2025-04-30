package com.zineyu.forum.backend.dto.user;


import com.zineyu.forum.backend.dto.post.UserPostPreviewDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserPostPageDto {
    private List<UserPostPreviewDto> posts;
    private long totalPages;
    private boolean hasNext;
    private boolean hasPrevious;
}
