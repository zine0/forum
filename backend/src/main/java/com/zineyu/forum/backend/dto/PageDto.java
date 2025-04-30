package com.zineyu.forum.backend.dto;

import com.zineyu.forum.backend.dto.post.PosPreviewtDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageDto {
    private List<PosPreviewtDto> posts;
    private long totalPages;
    private boolean hasNext;
    private boolean hasPrevious;
}
