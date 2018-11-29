package com.lzx.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.resource.HttpResource;

import javax.servlet.http.HttpServletResponse;

/**
 * @author lzx
 */

@CrossOrigin
@RestController
public class CroController {

    @GetMapping("/aa")
    public String aa(@RequestParam(defaultValue = "callback")String callBack, HttpServletResponse response){

        return callBack+"('hello')";

    }

    @GetMapping("/cors")
    public String cro(){
        return "asdfsdfsdfsfdfsdfssfds";
    }
}
