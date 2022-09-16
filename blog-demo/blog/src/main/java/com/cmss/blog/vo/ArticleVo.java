package com.cmss.blog.vo;

import com.cmss.blog.dao.pojo.Category;
import lombok.Data;

@Data
public class ArticleVo {

    private Long id;

    private String createDate;

    private Long authorId;

    private String title;

    private String summary;

    private String content;

    private Category category;

}
