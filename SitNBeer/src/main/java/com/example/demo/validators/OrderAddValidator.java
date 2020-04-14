package com.example.demo.validators;

import org.springframework.stereotype.Component;
import org.springframework.validation.Validator;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import com.example.demo.models.Bar;
import com.example.demo.models.Beer;
import com.example.demo.models.Order;

@Component
public class OrderAddValidator implements Validator{

    @Override
    public void validate(Object o, Errors errors){
        Order order = (Order)o;
        Beer beer = order.getBeer();

        if(beer == null){
            errors.rejectValue("beer", "beer", "You must select a beer ! ");
        }else{
            Bar bar = beer.getBar();

            ValidationUtils.rejectIfEmptyOrWhitespace(errors, "tableNumber", "tableNumber.empty", "You must enter a table number !");
            if(order.getTableNumber() < 0 || order.getTableNumber() > bar.getAvailableTable()){
                errors.rejectValue("tableNumber", "tableNumber.size", "The size must be between 2 and "+bar.getAvailableTable()+" !");
            }
        }
    }

    public boolean supports(Class<?> aClass){
        return Order.class.equals(aClass);
    }
}