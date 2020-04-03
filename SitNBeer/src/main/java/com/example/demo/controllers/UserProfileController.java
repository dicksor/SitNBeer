package com.example.demo.controllers;

import java.security.Principal;

import com.example.demo.models.User;
import com.example.demo.repositories.IUserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
class UserProfileController{

	@Autowired
	private IUserRepository userRepository;

    @GetMapping("/profile")
	public String index(Model model, Principal principal) {

		User user = userRepository.findByUsername(principal.getName());
		model.addAttribute("user", user);
		return "profile";
	}
}