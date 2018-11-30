package com.lzx.web;

import com.lzx.exception.MyException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
@Component
public class HandlerAndAdvice {

    // 本类使用@ControllerAdvice+ @ExceptionHandler的方式实现全局配置
    // 解耦合，配置更加灵活

    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST) //这里还可以配置响应的状态哦
    @ResponseBody
    public String catchRuntime() {
        return "运行时发生了不可描述的错误偶偶偶偶局";
    }


    @ExceptionHandler(MyException.class)
    public String catchMy(){
        return "myError";
    }
}
