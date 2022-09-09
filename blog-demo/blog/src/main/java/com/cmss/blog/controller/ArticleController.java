package com.cmss.blog.controller;

import com.cmss.blog.service.ArticleService;
import com.cmss.blog.vo.ArticleVo;
import com.cmss.blog.vo.CommenResult;
import com.cmss.blog.vo.params.ArticleParam;
import com.cmss.blog.vo.params.PageParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/articles")
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @GetMapping("all")
    public CommenResult getAll(){

        return articleService.getAllArticles();
    }
    @GetMapping
    public CommenResult getByPage(@RequestBody PageParam pageParam){

        return articleService.getArticleByPage(pageParam);
    }

    /**
     * 获取作者的所有文章
     * @param id
     * @return
     */
    @GetMapping("/{atuhorId}")
    public CommenResult getBlogByAuthorId(@PathVariable("atuhorId") Long id){

        return articleService.getBlogByAuthorId(id);
    }

    /**
     * 获取文章详情
     * @param id
     * @return
     */
    @GetMapping("/detail/{articleId}")
    public CommenResult getBlogByArticleId(@PathVariable("articleId") Long id){

        return articleService.getBlogByArticleId(id);
    }

    @PostMapping("publish")
    public CommenResult publish(@RequestBody ArticleParam articleParam , @RequestHeader("Authorization") String token){

        return articleService.publish(articleParam , token);
    }

    @PostMapping("modify")
    public CommenResult modify(@RequestBody ArticleParam articleParam , @RequestHeader("Authorization") String token){

        return articleService.modify(articleParam , token);
    }
}
