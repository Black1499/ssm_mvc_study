package com.lzx.annotation;

import javax.validation.Constraint;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.Payload;
import java.lang.annotation.*;
import java.util.regex.Pattern;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.ElementType.TYPE_USE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE})
@Retention(RUNTIME)
@Documented
@Constraint(validatedBy = {CellPhoneValid.class})
public @interface CellPhone {

    //自定义jsr-303验证注解
    String message() default "格式不正确，应该是11位";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}

class CellPhoneValid implements ConstraintValidator<CellPhone, String> {
    @Override
    public void initialize(CellPhone constraintAnnotation) {
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return value != null && Pattern.matches("^[0-9]{11}", value);
    }
}
