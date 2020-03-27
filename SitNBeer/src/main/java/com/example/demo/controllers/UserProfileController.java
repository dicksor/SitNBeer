package com.example.demo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
class UserProfileController{

    @GetMapping("/profile")
	public String index() {
		return "profile";
	}
}