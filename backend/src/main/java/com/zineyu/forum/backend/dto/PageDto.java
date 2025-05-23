package com.zineyu.forum.backend.dto;

import com.zineyu.forum.backend.dto.post.PostPreviewDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageDto {
    private List<PostPreviewDto> posts;
    private long totalPages;
    private boolean hasNext;
    private boolean hasPrevious;
}
