package com.cmss.blog.service;


import com.cmss.blog.vo.CommenResult;
import com.cmss.blog.vo.params.ArticleParam;
import com.cmss.blog.vo.params.PageParam;

public interface ArticleService {
    CommenResult getAllArticles();

    CommenResult getArticleByPage(PageParam pageParam);

    /**
     * 获取作者所有文章
     * @param id
     * @return
     */
    CommenResult getBlogByAuthorId(Long id);

    /**
     * 根据文章id 获取详情
     * @param id
     * @return
     */
    CommenResult getBlogByArticleId(Long id);

    CommenResult publish(ArticleParam articleParam , String token);

    CommenResult modify(ArticleParam articleParam , String token);
}
