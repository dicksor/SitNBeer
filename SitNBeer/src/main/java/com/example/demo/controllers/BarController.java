package com.example.demo.controllers;

import java.security.Principal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import com.example.demo.models.Bar;
import com.example.demo.models.User;
import com.example.demo.repositories.IUserRepository;
import com.example.demo.validators.BarAddValidator;

@Controller
class BarController{

	@Autowired
	private BarAddValidator barAddValidator;

	@Autowired
	private IUserRepository userRepository;

    @GetMapping("/bar/add")
	public String index(Model model) {
		model.addAttribute("bar", new Bar());
		return "bar";
	}

	@PostMapping("/bar/add")
	public String barSubmit(@ModelAttribute Bar bar, Model model, BindingResult bindingResult, Principal principal) {
		
		barAddValidator.validate(bar, bindingResult);

		if(bindingResult.hasErrors()){
			return "bar";
		}

		User loggedUser = userRepository.findByName(principal.getName());

		bar.setUser(loggedUser);
		
		return "/";
	}

}