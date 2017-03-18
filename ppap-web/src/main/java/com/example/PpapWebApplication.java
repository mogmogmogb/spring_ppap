package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@SpringBootApplication
@EnableDiscoveryClient
@RestController
@EnableCircuitBreaker
public class PpapWebApplication {

	@Autowired
	RestTemplate restTemplate;

	@RequestMapping(path = "/")
	@HystrixCommand(fallbackMethod = "error")
	public String hello() {
		String result = restTemplate.getForObject("http://ppap-service", String.class);

		return result + "from";
	}

	public String error() {
		return "ppap-service is DOWN!!";
	}

	public static void main(String[] args) {
		SpringApplication.run(PpapWebApplication.class, args);
	}
}
