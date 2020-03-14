package com.example.demo.controllers;

import java.security.Principal;
import java.util.Optional;

import com.example.demo.models.Bar;
import com.example.demo.models.Beer;
import com.example.demo.models.Order;
import com.example.demo.models.enums.OrderStatusEnum;
import com.example.demo.repositories.IBarRepository;
import com.example.demo.repositories.IBeerRepository;
import com.example.demo.repositories.IOrderRepository;
import com.example.demo.validators.OrderAddValidator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
class CommandController{

	@Autowired
	private IBeerRepository beerRepository;

	@Autowired
	private IOrderRepository orderRepository;

	@Autowired 
	private OrderAddValidator orderAddValidator;

	@GetMapping("/orders")
	public String orders(Model model){
		/*List<Order> listOrders = orderRepository.findAll();
		System.out.println(listOrders.toString());
		//model.addAttribute("orders", listOrders);*/
		return "home";
	}

	@PostMapping("/order/add")
	public String addOrder(@ModelAttribute Order order, @RequestParam("order-beer") Integer beerId, Model model, BindingResult bindingResult, Principal principal){

		Optional<Beer> optionalBeer = beerRepository.findById(beerId);
		if(optionalBeer.isPresent()){
			order.setBeer(optionalBeer.get());
		}
		
		orderAddValidator.validate(order, bindingResult);

		if(bindingResult.hasErrors()){
			return "showBar";
		}
		order.setStatus(OrderStatusEnum.OPEN);
		//order.setUser(new User());

		return "home";
	}
}