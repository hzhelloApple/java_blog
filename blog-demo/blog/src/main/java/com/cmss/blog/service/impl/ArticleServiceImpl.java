package com.cmss.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cmss.blog.dao.mapper.ArticleBodyMapper;
import com.cmss.blog.dao.mapper.ArticleMapper;
import com.cmss.blog.dao.mapper.CategoryMapper;
import com.cmss.blog.dao.pojo.Article;
import com.cmss.blog.dao.pojo.ArticleBody;
import com.cmss.blog.dao.pojo.Category;
import com.cmss.blog.service.ArticleService;
import com.cmss.blog.utils.JWTUtil;
import com.cmss.blog.vo.ArticleVo;
import com.cmss.blog.vo.CommenResult;
import com.cmss.blog.vo.ErrorCode;
import com.cmss.blog.vo.params.ArticleParam;
import com.cmss.blog.vo.params.PageParam;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private ArticleMapper articleMapper;

    @Autowired
    private ArticleBodyMapper articleBodyMapper;


    @Override
    public CommenResult getAllArticles() {
        LambdaQueryWrapper<Article> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.orderByDesc(Article::getCreateDate);
        List<Article> articles = articleMapper.selectList(lambdaQueryWrapper);
        return CommenResult.success(copylist(articles, false));
    }

    @Override
    public CommenResult getArticleByPage(PageParam pageParam) {
        LambdaQueryWrapper<Article> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        Page<Article> page = new Page<>(pageParam.getPage(), pageParam.getPageSize());
        Page<Article> articlePage = articleMapper.selectPage(page, lambdaQueryWrapper);
        List<Article> records = articlePage.getRecords();
        return CommenResult.success(copylist(records , true));
    }

    @Override
    public CommenResult getBlogByAuthorId(Long id) {
        LambdaQueryWrapper<Article> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Article::getAuthorId , id);
        List<Article> articles = articleMapper.selectList(lambdaQueryWrapper);
        return CommenResult.success(copylist(articles));
    }

    @Override
    public CommenResult getBlogByArticleId(Long id) {

        LambdaQueryWrapper<Article> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Article::getId , id);
        Article article = articleMapper.selectOne(lambdaQueryWrapper);

        ArticleBody articleBodyById = getArticleBodyById(article.getBodyId());

        ArticleVo articleVo = new ArticleVo();
        BeanUtils.copyProperties(article , articleVo);
        articleVo.setContent(articleBodyById.getContent());
        return CommenResult.success(articleVo);
    }

    private ArticleBody getArticleBodyById(Long id) {

        LambdaQueryWrapper<ArticleBody> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(ArticleBody::getId , id);
        ArticleBody articleBody = articleBodyMapper.selectOne(lambdaQueryWrapper);
        return articleBody;
    }

    @Override
    public CommenResult publish(ArticleParam articleParam , String token) {

        String userId = String.valueOf(JWTUtil.checkToken(token).get("userId"));
        if (StringUtils.isBlank(userId)){
            return CommenResult.fail(ErrorCode.TOKEN_ERROR.getCode() , ErrorCode.TOKEN_ERROR.getMsg());
        }

        Article article = new Article();
        BeanUtils.copyProperties(articleParam , article);
        article.setAuthorId(Long.parseLong(userId));
        article.setCreateDate(currentTime());
        articleMapper.insert(article);

        ArticleBody articleBody = new ArticleBody();
        articleBody.setArticleId(article.getId());
        articleBody.setContent(articleParam.getContent());
        articleBodyMapper.insert(articleBody);

        article.setBodyId(articleBody.getId());
        articleMapper.updateById(article);

        return CommenResult.success(article.getId());
    }

    @Override
    public CommenResult modify(ArticleParam articleParam , String token) {

        String userId = String.valueOf(JWTUtil.checkToken(token).get("userId"));
        if (StringUtils.isBlank(userId)){
            return CommenResult.fail(ErrorCode.TOKEN_ERROR.getCode() , ErrorCode.TOKEN_ERROR.getMsg());
        }

        //更新article
        Article article = new Article();
        BeanUtils.copyProperties(articleParam , article);
        article.setAuthorId(Long.parseLong(userId));
        article.setCreateDate(currentTime());
        articleMapper.updateById(article);

        //更新articleBody
        LambdaQueryWrapper<ArticleBody> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(ArticleBody::getArticleId , article.getId());
        ArticleBody articleBody = articleBodyMapper.selectOne(queryWrapper);
        articleBody.setContent(articleParam.getContent());
        articleBodyMapper.updateById(articleBody);

        return CommenResult.success(article.getId());
    }


    public static void main(String[] args) {
        Date date = new Date();
        String dateFormat = "yyyy-MM-dd HH:mm:ss";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dateFormat);
        String date_str1 = simpleDateFormat.format(date);
        System.out.println(date_str1);
    }

    private String currentTime() {
        Date date = new Date();
        String dateFormat = "yyyy-MM-dd HH:mm:ss";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dateFormat);
        String date_str = simpleDateFormat.format(date);
        return date_str;
    }


    private List<ArticleVo> copylist(List<Article> articles) {
        List<ArticleVo> articleVos = new ArrayList<>();
        for (Article article : articles) {
            articleVos.add(copy(article , false));
        }
        return articleVos;
    }

    @Autowired
    private CategoryMapper categoryMapper;

    private ArticleVo copy(Article article , boolean isCategory) {
        ArticleVo articleVo = new ArticleVo();
        BeanUtils.copyProperties(article , articleVo);
        if (isCategory){
            LambdaQueryWrapper<Category> lambdaQueryWrapper = new LambdaQueryWrapper<>();
            lambdaQueryWrapper.eq(Category::getId , article.getCategoryId());
            Category category = categoryMapper.selectOne(lambdaQueryWrapper);
            articleVo.setCategory(category);
        }
        return articleVo;
    }

    private List<ArticleVo> copylist(List<Article> articles , boolean isCategory) {
        List<ArticleVo> articleVos = new ArrayList<>();
        for (Article article : articles) {
            articleVos.add(copy(article , isCategory));
        }
        return articleVos;
    }
}
