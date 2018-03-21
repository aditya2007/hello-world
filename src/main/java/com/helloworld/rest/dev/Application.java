package com.helloworld.rest.dev;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//@Configuration
@SpringBootApplication(scanBasePackages = {"com.helloworld.rest.dev"})
//@EnableAspectJAutoProxy(proxyTargetClass = false)
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}
