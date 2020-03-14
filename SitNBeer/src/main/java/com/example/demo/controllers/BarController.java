package com.example.demo.controllers;

import java.security.Principal;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.demo.models.Bar;
import com.example.demo.models.Beer;
import com.example.demo.models.Order;
import com.example.demo.repositories.IBarRepository;
import com.example.demo.repositories.IBeerRepository;
import com.example.demo.repositories.IUserRepository;
import com.example.demo.validators.BarAddValidator;

@Controller
class BarController{

	@Autowired
	private BarAddValidator barAddValidator;

	@Autowired
	private IBarRepository barRepository;

	@Autowired
	private IBeerRepository beerRepository;

	@Autowired
	private IUserRepository userRepository;

    @GetMapping("/bar/add")
	public String addBarForm(Model model) {
		model.addAttribute("bar", new Bar());
		return "createBar";
	}

	@PostMapping("/bar/add")
	public String addBar(@ModelAttribute Bar bar, Model model, BindingResult bindingResult, Principal principal) {

		barAddValidator.validate(bar, bindingResult);

		if(bindingResult.hasErrors()){
			return "createBar";
		}

		/*User loggedUser = userRepository.findByName(principal.getName());

		bar.setUser(loggedUser);
		barRepository.save(bar);*/

		return "redirect:/";
	}

	@GetMapping("/bar/{bar_id}")
	public String showBar(Model model, @PathVariable Integer bar_id){
		Optional<Bar> optionalBar = barRepository.findById(bar_id);
		Bar bar = null;
		if(optionalBar.isPresent()){
			bar = optionalBar.get();
		}else {
			return "redirect:/";
		}

		Iterable<Beer> beers = bar.getBeers();

		model.addAttribute("bar", bar);
		model.addAttribute("order", new Order());
		model.addAttribute("beers", beers);

		return "showBar";
	}
}