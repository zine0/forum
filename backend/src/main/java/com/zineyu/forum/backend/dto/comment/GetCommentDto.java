package com.zineyu.forum.backend.dto.comment;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetCommentDto {
    private Integer id;
    private String authorName;
    private String authorAvatar;
    private String content;
}
