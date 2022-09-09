package com.cmss.blog.dao.pojo;

import lombok.Data;

@Data
public class ArticleBody {

    private Long id;

    private Long articleId;

    private String content;
}
