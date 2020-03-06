package com.example.demo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import com.example.demo.models.Bar;

@Controller
class BarController{

    @GetMapping("/bar/add")
	public String index(Model model) {
		model.addAttribute("bar", new Bar());
		return "bar";
	}

	@PostMapping("/bar/add")
	public String barSubmit(@ModelAttribute Bar bar, Model model) {
		return "result";
	}

}