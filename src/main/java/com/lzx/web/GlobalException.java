package com.lzx.web;

import com.lzx.exception.MyException;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

//@Component // 如果不加改注解，此异常不起作用
public class GlobalException implements HandlerExceptionResolver {

    // 自定义全局异常处理在mvc一直起作用
    @Override
    public ModelAndView resolveException(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) {
        if (e instanceof MyException) {
            return new ModelAndView("error1");
        } else if (e instanceof RuntimeException){
            return new ModelAndView("error2");
        }
        return new ModelAndView("error3");
    }
}
