package com.zineyu.forum.backend.pojo;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("article_likes")
public class PostLike {
    @TableId(type = IdType.AUTO)
    private Integer id;

    private Integer userId;
    private Integer postId;

    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;
}
