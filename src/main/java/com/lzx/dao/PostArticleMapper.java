package com.lzx.dao;

import com.lzx.entity.PostArticle;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface PostArticleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(PostArticle record);

    PostArticle selectByPrimaryKey(Integer id);

    List<PostArticle> selectAll();

    int updateByPrimaryKey(PostArticle record);
}