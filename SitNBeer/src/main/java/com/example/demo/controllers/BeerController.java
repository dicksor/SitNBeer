package com.example.demo.controllers;

import java.security.Principal;

import com.example.demo.models.Beer;
import com.example.demo.repositories.IBeerRepository;
import com.example.demo.repositories.IUserRepository;
import com.example.demo.validators.BeerAddValidator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
class BeerController{

    @Autowired
	private IBeerRepository beerRepository;

    @Autowired
    private IUserRepository userRepository;

    @Autowired
	private BeerAddValidator beerAddValidator;

    @GetMapping("/beer/add")
	public String addBeerForm(Model model) {
        model.addAttribute("beer", new Beer());
		return "beer";
    }


    @PostMapping("/beer/add")
    public String addBeer(@ModelAttribute Beer beer, Model model, BindingResult bindingResult, Principal principal){

        beerAddValidator.validate(beer, bindingResult);

		if(bindingResult.hasErrors()){
			return "beer";
		}

        return "redirect:/";
    }
}