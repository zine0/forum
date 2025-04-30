package com.zineyu.forum.backend.dto.post;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PosPreviewtDto {
    private int id;
    private String title;
    private String preview;
    private long authorId;
    private String authorName;
    private String authorAvatar;
}
