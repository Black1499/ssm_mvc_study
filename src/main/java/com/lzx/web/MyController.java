package com.lzx.web;

import com.lzx.dao.PostArticleMapper;
import com.lzx.dao.PostAuthorMapper;
import com.lzx.entity.PostArticle;
import com.lzx.entity.PostAuthor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.util.List;

@Controller
public class MyController {

    @Autowired
    PostArticleMapper articleMapper;
    @Autowired
    PostAuthorMapper authorMapper;


    @Autowired
    ApplicationContext applicationContext;
    @Autowired
    DataSource dataSource;

    @Resource
    RequestMappingHandlerMapping requestMappingHandlerMapping;

    @RequestMapping("/a")
    public String handlerRequest(Model model){
        String[] beanDefinitionNames = applicationContext.getBeanDefinitionNames();
        for (String beanDefinitionName : beanDefinitionNames) {
            System.out.println(">>>>"+beanDefinitionName);
        }
        ApplicationContext parent = applicationContext.getParent();
        for (String beanDefinitionName : parent.getBeanDefinitionNames()) {
            System.out.println("--- " + beanDefinitionName);
        }

        List<PostAuthor> authors = authorMapper.selectAll();
        List<PostArticle> articles = articleMapper.selectAll();
        model.addAttribute("authors", authors);
        model.addAttribute("articles", articles);
        return "index";
    }

    @RequestMapping("/b")
    public String requestParament(Model model, PostAuthor author){
        System.out.println("as");
        return "";
    }


    @RequestMapping("/c")
    public String setParament(RedirectAttributes redirectAttributes){
        redirectAttributes.addAttribute("a","16a");
        return "redirect:/a";
    }

    @RequestMapping("/d")
    public String setd(Model model){
        return "index";
    }
}
