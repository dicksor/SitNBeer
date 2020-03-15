package com.example.demo.controllers;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import com.example.demo.models.Bar;
import com.example.demo.models.Beer;
import com.example.demo.models.Order;
import com.example.demo.models.enums.OrderStatusEnum;
import com.example.demo.repositories.IBarRepository;
import com.example.demo.repositories.IBeerRepository;
import com.example.demo.repositories.IOrderRepository;
import com.example.demo.validators.OrderAddValidator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
class OrderController{

	@Autowired
	private IBeerRepository beerRepository;

	@Autowired
	private IBarRepository barRepository;

	@Autowired
	private IOrderRepository orderRepository;

	@Autowired 
	private OrderAddValidator orderAddValidator;

	@GetMapping("/orders/{bar_id}")
	public String orders(Model model,  @PathVariable Integer bar_id){
		Optional<Bar> optionalBar = barRepository.findById(bar_id);
		if(optionalBar.isPresent()){
			Bar bar = optionalBar.get();
			List<Order> orders = bar.getOrders();
			model.addAttribute("orders", orders);
			model.addAttribute("isEntreprise", true);
			System.out.println(orders);
			return "orders";
		}
		
		return "home";
	}

	@GetMapping(value = "/order/accept/{order_id}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public String orderAccept(@PathVariable long order_id){
		String status = "{status:'ERROR'}";
		Optional<Order> optionalOrder = orderRepository.findById(order_id);
		if(optionalOrder.isPresent()){
			Order order = optionalOrder.get();
			order.setStatus(OrderStatusEnum.IN_PROCESS);
			System.out.println(order.getStatus());
			status = "{status:'OK'}";
		}
		return status;
	}

	@PostMapping("/order/add")
	public String addOrder(@ModelAttribute Order order, @RequestParam("order-beer") long beerId, Model model, BindingResult bindingResult, Principal principal){

		Optional<Beer> optionalBeer = beerRepository.findById(beerId);
		if(optionalBeer.isPresent()){
			Beer beer = optionalBeer.get();
			order.setBeer(beer);
			order.setBar(beer.getBar());
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