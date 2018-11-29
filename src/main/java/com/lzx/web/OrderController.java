package com.lzx.web;

import com.lzx.entity.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.ThemeResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.Locale;

@Controller
public class OrderController {

    @PostMapping ("/addOrder")
    public String order (@Valid Order order, BindingResult result) {
        if(result.hasErrors()){
            return "orderForm";
        }
        return "index";
    }

    @GetMapping("/order")
    public String orderIndex(Model model,Locale locale){
        model.addAttribute("msg", "嘟噜噜");
        model.addAttribute("order",new Order("12","预输入","email",12));
        return "orderForm";
    }


    @Autowired
    private LocaleResolver localeResolver;


    @Autowired
    private ThemeResolver themeResolver;

    @GetMapping("/language/{lg}")
    public String changeLanguage(@PathVariable("lg") String language, HttpServletRequest req, HttpServletResponse resp){
        Locale locale = new Locale(language);
        localeResolver.setLocale(req, resp, locale);
        return "redirect: /order";
    }


    @GetMapping("/themes/{css}")
    public String changeCss(@PathVariable("css") String css, HttpServletRequest req, HttpServletResponse resp) {
        themeResolver.setThemeName(req, resp, css);
        return "redirect:/order";
    }

}
