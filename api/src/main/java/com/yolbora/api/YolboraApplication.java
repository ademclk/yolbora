package com.yolbora.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.yolbora")
public class YolboraApplication {

	public static void main(String[] args) {
		SpringApplication.run(YolboraApplication.class, args);
	}

}
