package com.example.demo.validators;

import com.example.demo.models.Beer;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class BeerAddValidator implements Validator{
    @Override
    public void validate(Object o, Errors errors){
        Beer beer = (Beer)o;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "name.empty", "You must enter a beer name !");
        if(beer.getName().length() < 2 || beer.getName().length() >32){
            errors.rejectValue("name", "name.size", "The size of name must be between 2 and 32 !");
        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "manufacturer", "manufacturer.empty", "You must enter a beer manufacturer !");
        if(beer.getManufacturer().length() < 2 || beer.getManufacturer().length() >255){
            errors.rejectValue("manufacturer", "manufacturer.size", "The of manufacturer size must be between 2 and 255 !");
        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "price", "price.empty", "You must enter a beer price !");
        if(beer.getPrice() < 0 || beer.getPrice() > 255){
            errors.rejectValue("price", "price.size", "The size of price must be between 0 and 255 !");
        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "volume", "volume.empty", "You must enter a beer volume !");
        if(beer.getVolume() < 0 || beer.getVolume() > 255){
            errors.rejectValue("volume", "volume.size", "The size of volume must be between 0 and 255 !");
        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "stockQuantity", "stockQuantity.empty", "You must enter a beer stock quantity !");
        if(beer.getStockQuantity() < 0 || beer.getStockQuantity() > 255){
            errors.rejectValue("stockQuantity", "stockQuantity.size", "The size must be between 0 and 255 !");
        }
    }

    public boolean supports(Class<?> aClass){
        return Beer.class.equals(aClass);
    }
}