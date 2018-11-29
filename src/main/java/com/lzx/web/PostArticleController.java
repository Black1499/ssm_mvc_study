package com.lzx.web;

import com.lzx.dao.PostArticleMapper;
import com.lzx.dao.PostAuthorMapper;
import com.lzx.entity.PostArticle;
import com.lzx.entity.PostAuthor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
@RequestMapping("/")
public class PostArticleController {

    @Autowired
    PostArticleMapper articleMapper;
    @Autowired
    PostAuthorMapper authorMapper;

    @RequestMapping(method = RequestMethod.GET)
    public String index(Model model){
        List<PostAuthor> authors = authorMapper.selectAll();
        List<PostArticle> articles = articleMapper.selectAll();
        model.addAttribute("authors", authors);
        model.addAttribute("articles", articles);
        return "index";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String addArticle(PostArticle article){
        articleMapper.insert(article);
        return  "redirect:/";
    }
}
