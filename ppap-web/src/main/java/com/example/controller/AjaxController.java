package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
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
	@HystrixCommand(fallbackMethod = "errorPpap")
	@ResponseBody
	public String[] ppap() {
		String result = restTemplate.getForObject("http://ppap-service", String.class);
		String[] resultList = { result };

		return resultList;
	}

	@RequestMapping(path = "concat", consumes = MediaType.APPLICATION_JSON_VALUE)
	@HystrixCommand(fallbackMethod = "errorConcat")
	@ResponseBody
	public String[] concat(@RequestBody ConcatBean bean) {
		String result = restTemplate.postForObject("http://concat-service", bean, String.class);
		String[] resultList = { result };

		return resultList;
	}

	public String[] errorPpap() {

		return new String[] { "ppap-service is DOWN!!" };
	}

	public String[] errorConcat(ConcatBean bean) {

		return new String[] { "concat-service is DOWN!!" };
	}

}
