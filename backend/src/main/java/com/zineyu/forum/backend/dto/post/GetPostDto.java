package com.zineyu.forum.backend.dto.post;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetPostDto {
    private int id;
    private String title;
    private String content;
    private String authorName;
    private String authorAvatar;
}
