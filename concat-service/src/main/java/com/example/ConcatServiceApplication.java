package com.example;

import java.util.UUID;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableDiscoveryClient
@RestController
public class ConcatServiceApplication {

	private static final UUID id = UUID.randomUUID();

	public static void main(String[] args) {
		SpringApplication.run(ConcatServiceApplication.class, args);
	}

	@RequestMapping(path = "/", consumes = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
	String concat(@RequestBody ConcatBean bean) {
		return "concat-service :" + bean.getValue1() + bean.getValue2();
	}

	static public class ConcatBean {
		private String value1;
		private String value2;

		public String getValue1() {
			return value1;
		}

		public void setValue1(String value1) {
			this.value1 = value1;
		}

		public String getValue2() {
			return value2;
		}

		public void setValue2(String value2) {
			this.value2 = value2;
		}
	}
}
