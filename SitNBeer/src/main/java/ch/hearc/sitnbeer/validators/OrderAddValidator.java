/**
 * SitNBeer
 * Romain Capocasale, Vincent Moulin and Jonas Freiburghaus
 * He-Arc, INF3dlm-a
 * Spring Course
 * 2019-2020
 */
package ch.hearc.sitnbeer.validators;

import org.springframework.stereotype.Component;
import org.springframework.validation.Validator;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import ch.hearc.sitnbeer.models.Bar;
import ch.hearc.sitnbeer.models.Beer;
import ch.hearc.sitnbeer.models.Order;

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