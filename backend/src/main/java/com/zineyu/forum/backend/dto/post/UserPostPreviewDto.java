package com.zineyu.forum.backend.dto.post;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserPostPreviewDto {
    private Integer id;
    private String title;
    private String preview;
    private Integer authorId;
    private Integer likes;
}
