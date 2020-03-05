package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
class UserController{

    @RequestMapping("/login")
	public String login() {
		return "Hello login";
    }

    @RequestMapping("/register")
	public String register() {
		return "Hello register";
	}

}