package com.cmss.blog.vo;

import lombok.Data;

@Data
public class ArticleVo {

    private Long id;

    private String createDate;

    private String authorId;

    private String title;

    private String summary;

    private String content;

}
