package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

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

	@HystrixCommand(fallbackMethod = "error")
	public String hello() {
		String result = restTemplate.getForObject("http://ppap-service", String.class);

		return result + "from";
	}

	public String error() {
		return "ppap-service is DOWN!!";
	}
}
