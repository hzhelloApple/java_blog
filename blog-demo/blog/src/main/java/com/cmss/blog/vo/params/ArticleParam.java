package com.cmss.blog.vo.params;

import lombok.Data;

@Data
public class ArticleParam {

    private Long id;

    private String title;

    private String summary;

    private String content;
}
