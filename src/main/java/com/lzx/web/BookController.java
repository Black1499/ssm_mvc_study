package com.lzx.web;

import com.lzx.entity.Book;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
public class BookController {

    @PostMapping("/addBook")
    // 注意这里一定要使用@valid注解，否则jsr303验证不起作用
    // BindingResult绑定的结果，可以查看源代码进行理解
    public String addBook(@Valid Book book, BindingResult result){
        // 如果有错误返回页面，错误信息也会返回
        if(result.hasErrors()){
            return "bookForm";
        }
        return "正确的页面";
    }

    @GetMapping("/bookForm")
    public String bookForm(Model model){
        model.addAttribute("book", new Book());
        return "bookForm";
    }
}
