package com.cmss.blog.dao.pojo;

import lombok.Data;

@Data
public class Article {

    private Long id;

    private Long authorId;

    private String createDate;

    private String title;

    private String summary;

    private Long bodyId;

    private Integer viewCounts;

    private Integer categoryId;
}
