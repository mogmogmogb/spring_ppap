package com.example;

import java.util.UUID;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableDiscoveryClient
@RestController
public class PpapServiceApplication {
	private static final UUID id = UUID.randomUUID();

	public static void main(String[] args) {
		SpringApplication.run(PpapServiceApplication.class, args);
	}

	@RequestMapping(path = "/")
	String ppap() {
		return "ppap-service @" + id;
	}
}
