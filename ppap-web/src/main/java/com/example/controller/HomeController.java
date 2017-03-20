package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;

@EnableDiscoveryClient
@Controller
@EnableCircuitBreaker
@RequestMapping("home")
public class HomeController {

	@Autowired
	RestTemplate restTemplate;

	@RequestMapping(method = RequestMethod.GET)
	public String home(Model model) {
		return "home/index";
	}

}
