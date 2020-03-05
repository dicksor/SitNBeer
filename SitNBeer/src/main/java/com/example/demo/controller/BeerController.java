package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
class BeerController{

    @RequestMapping("/beer")
	public String add() {
		return "beer";
    }

}