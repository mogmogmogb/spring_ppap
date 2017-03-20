package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@EnableDiscoveryClient
@RestController
@EnableCircuitBreaker
@RequestMapping("ajax")
public class AjaxController {

	@Autowired
	RestTemplate restTemplate;

	@RequestMapping(path = "ppap")
	@HystrixCommand(fallbackMethod = "error")
	@ResponseBody
	public String[] ppap() {
		String result = restTemplate.getForObject("http://ppap-service", String.class);
		String[] resultList = { result };

		return resultList;// result + "from";
	}

	public String[] error() {

		return new String[] { "ppap-service is DOWN!!" };
	}
}
