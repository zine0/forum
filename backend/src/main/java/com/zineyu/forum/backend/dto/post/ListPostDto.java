package com.zineyu.forum.backend.dto.post;

import com.zineyu.forum.backend.dto.user.AuthorDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ListPostDto {
    private int id;
    private AuthorDto author;
    private String title;
    private String content;
}
