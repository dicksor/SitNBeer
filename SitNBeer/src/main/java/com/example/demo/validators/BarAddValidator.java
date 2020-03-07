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
        Bar bar = (Bar)o;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "name.empty", "You must enter a bar name !");
        if(bar.getName().length() < 2 || bar.getName().length() >32){
            errors.rejectValue("name", "name.size", "The size must be between 2 and 32 !");
        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "address", "address.empty", "You must enter a bar adress !");
        if(bar.getAddress().length() < 10 || bar.getAddress().length() > 255){
            errors.rejectValue("address", "address.size", "The size must be between 10 and 255 !");
        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "availableTable", "availableTable.empty", "You must enter number of available table !");
        if(bar.getAvailableTable() < 1 || bar.getAvailableTable() > 255){
            errors.rejectValue("availableTable", "availableTable.size", "The size must be between 1 and 255 !");
        }
    }

    public boolean supports(Class<?> aClass){
        return Bar.class.equals(aClass);
    }
}