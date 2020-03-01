package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
class CommandController{

	@RequestMapping("/command")
	public String index() {
		return "Hello command";
	}

}