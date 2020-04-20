/**
 * SitNBeer
 * Romain Capocasale, Vincent Moulin and Jonas Freiburghaus
 * He-Arc, INF3dlm-a
 * Spring Course
 * 2019-2020
 */

package ch.hearc.sitnbeer.controllers;

import java.security.Principal;
import java.util.Optional;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import ch.hearc.sitnbeer.models.Bar;
import ch.hearc.sitnbeer.models.User;
import ch.hearc.sitnbeer.models.enums.RoleEnum;
import ch.hearc.sitnbeer.repositories.IBarRepository;
import ch.hearc.sitnbeer.repositories.IRoleRepository;
import ch.hearc.sitnbeer.repositories.IUserRepository;
import ch.hearc.sitnbeer.services.interfaces.ISecurityService;
import ch.hearc.sitnbeer.services.interfaces.IUserService;
import ch.hearc.sitnbeer.validators.UserValidator;
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

    // Constantes
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
    public String delete(Principal principal, HttpServletRequest request) throws ServletException {
        User user = userRepository.findByUsername(principal.getName());

        if (user.getRole() == roleRepository.findByRole(RoleEnum.ENTERPRISE.toString())) {
            Optional<Bar> optionalBar = barRepository.findByUser(user);
            if (optionalBar.isPresent()) {
                barRepository.delete(optionalBar.get());
            }
        }

        request.logout();

        userRepository.delete(user);

        return "redirect:/home";
    }
}
