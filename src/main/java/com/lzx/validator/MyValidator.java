package com.lzx.validator;

import com.lzx.entity.Order;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.regex.Pattern;

public class MyValidator implements Validator {

    @Override
    public boolean supports(Class<?> aClass) {
        if(aClass == MyValidator.class){
            return true;
        }
        return false;
    }

    @Override
    public void validate(Object o, Errors errors) {
        if(o == null){
            errors.rejectValue("order",null, "对象不为空");
        }
        Order order = (Order)o;
        if (order.getId() == null || order.getId().isEmpty()) {
            errors.rejectValue("id",null,"编号不能为空");
        }else if(order.getName() == null || order.getName().isEmpty()){
            errors.rejectValue("name",null,"姓名不能为空");
        }else if(order.getEmail() == null || Pattern.matches("^[a-zA-z0-9]+@[a-zA-z0-9]+.com$", order.getEmail())){
            errors.rejectValue("email", null, "email格式错误");
        }else if(order.getPrice()<0){
            errors.rejectValue("price",null , "价格不能为负数");
        }
    }
}
