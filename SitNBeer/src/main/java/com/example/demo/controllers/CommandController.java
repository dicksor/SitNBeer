package com.example.demo.controllers;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Optional;

import com.example.demo.models.Bar;
import com.example.demo.models.Beer;
import com.example.demo.models.Order;
import com.example.demo.repositories.IBarRepository;
import com.example.demo.repositories.IBeerRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
class CommandController{

	@Autowired
	private IBeerRepository beerRepository;

	@Autowired
	private IBarRepository barRepository;

	@PostMapping("/order/add")
	public String addOrder(@ModelAttribute Order order, Model model, BindingResult bindingResult, Principal principal){
		return "";
	}
}