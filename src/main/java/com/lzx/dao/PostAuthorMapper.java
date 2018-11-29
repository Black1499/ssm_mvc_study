package com.lzx.dao;

import com.lzx.entity.PostAuthor;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface PostAuthorMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(PostAuthor record);

    PostAuthor selectByPrimaryKey(Integer id);

    List<PostAuthor> selectAll();

    int updateByPrimaryKey(PostAuthor record);
}