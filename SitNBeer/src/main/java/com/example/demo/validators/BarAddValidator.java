package com.example.demo.validators;

import org.springframework.stereotype.Component;
import org.springframework.validation.Validator;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import com.example.demo.models.Bar;

@Component
public class BarAddValidator implements Validator{
    @Override
    public void validate(Object o, Errors errors){
        Bar 
    }

    public boolean supports(Class<?> aClass){
        return Bar.class.equals(aClass);
    }
}