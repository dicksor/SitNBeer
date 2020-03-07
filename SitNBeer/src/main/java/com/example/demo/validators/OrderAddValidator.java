package com.example.demo.validators;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Validator;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import com.example.demo.models.Bar;
import com.example.demo.models.Beer;
import com.example.demo.models.Order;
import com.example.demo.repositories.IBarRepository;
import com.example.demo.repositories.IBeerRepository;

@Component
public class OrderAddValidator implements Validator{

    @Autowired
    private IBarRepository barRepository;
    
    @Autowired
	private IBeerRepository beerRepository;

    @Override
    public void validate(Object o, Errors errors){
        Order order = (Order)o;
        /* TODO: get beer and bar object for table number validation
        Beer beer = beerRepository.findById(order.get)
        Bar bar = barRepository.findby*/

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "table", "table.empty", "You must enter a table number !");
        if(order.getTable() < 0 || order.getTable() > 32){
            errors.rejectValue("table", "table.size", "The size must be between 2 and 32 !");
        }
    }

    public boolean supports(Class<?> aClass){
        return Order.class.equals(aClass);
    }
}