package com.example.demo.validators;

import com.example.demo.models.User;
import com.example.demo.services.interfaces.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class UserValidator implements Validator {
    
    @Autowired
    private IUserService userService;

    private static final String USERNAME = "username";
    private static final String EMAIL = "email";

    @Override
    public boolean supports(Class<?> aClass) {
        return User.class.equals(aClass);
    }

    @Override
	public void validate(Object o, Errors errors) {
		
		User user = (User) o;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, USERNAME, "username.empty", "You must enter a username!");
        if (user.getUsername().length() < 4 || user.getUsername().length() > 32) {
            errors.rejectValue(USERNAME, "username.size", "The size must be between 4 and 32!");
        }

        if (userService.findByUsername(user.getUsername()) != null) {
        	errors.rejectValue(USERNAME, "username.dupplicate", "This username is already used!");
        }
        
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "email.empty", "You must enter an email!");
        if(!isValidEmail(user.getEmail())) {
        	errors.rejectValue(EMAIL, "email.structure", "This is not a valid email!");
        }
        
        if (userService.findByEmail(user.getEmail()) != null) {
        	errors.rejectValue(EMAIL, "email.dupplicate", "This email is already used!");
        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "password.empty", "Password can't be empty!");
        if (user.getPassword().length() < 8 || user.getPassword().length() > 32) {
        	errors.rejectValue("password", "password.length", "The size must be between 8 and 32!");
        }

        if (!user.getPasswordConfirm().equals(user.getPassword())) {
        	errors.rejectValue("passwordConfirm", "passwordConfirm.value", "This password doesn't match!");
        }
        
	}
	
	private boolean isValidEmail(String email)
	{
		String regex = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
	    return email.matches(regex);
	}

}
