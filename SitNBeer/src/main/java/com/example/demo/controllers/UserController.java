package com.example.demo.controllers;

import java.io.IOException;
import java.security.Principal;
import java.util.Optional;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

import com.example.demo.models.Bar;
import com.example.demo.models.Role;
import com.example.demo.models.User;
import com.example.demo.models.enums.RoleEnum;
import com.example.demo.repositories.IBarRepository;
import com.example.demo.repositories.IRoleRepository;
import com.example.demo.repositories.IUserRepository;
import com.example.demo.services.interfaces.ISecurityService;
import com.example.demo.services.interfaces.IUserService;
import com.example.demo.validators.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
public class UserController {
    @Autowired
    private IUserService userService;

    @Autowired
    private ISecurityService securityService;

    @Autowired
    private UserValidator userValidator;

    @Autowired
    private IUserRepository userRepository;
    
    @Autowired 
    private IBarRepository barRepository;

    @Autowired
    private IRoleRepository roleRepository;

    //Routes
    private static final String REGISTER = "register";
    private static final String LOGIN = "login";

    @GetMapping("/registration")
    public String registration(Model model) {
        model.addAttribute("userForm", new User());

        return REGISTER;
    }

    @PostMapping("/registration")
    public String registration(@ModelAttribute("userForm") User userForm, BindingResult bindingResult) {

        userValidator.validate(userForm, bindingResult);

        if (bindingResult.hasErrors()) {
            return REGISTER;
        }

        userService.save(userForm);

        securityService.autoLogin(userForm.getUsername(), userForm.getPassword());

        return "redirect:/home";
    }

    @GetMapping("/login")
    public String login(Model model, String error, String logout) {
        if (error != null)
            model.addAttribute("error", "Your username and password is invalid.");

        if (logout != null)
            model.addAttribute("message", "You have been logged out successfully.");

        return LOGIN;
    }

    @GetMapping("/user/delete")
    public String delete(Principal principal,HttpServletRequest request) throws ServletException{
        User user = userRepository.findByUsername(principal.getName());
        
        if(user.getRole() == roleRepository.findByRole(RoleEnum.ENTERPRISE.toString()))
        {
            Optional<Bar> optionalBar = barRepository.findByUser(user);
            if(optionalBar.isPresent())
            {
                barRepository.delete(optionalBar.get());
            }
        }

        request.logout();

        userRepository.delete(user);

        return "redirect:/home";
    }
}
