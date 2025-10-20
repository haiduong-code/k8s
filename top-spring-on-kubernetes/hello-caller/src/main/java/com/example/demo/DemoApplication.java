package com.example.demo;

import reactor.core.publisher.Mono;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

@SpringBootApplication
@RestController
public class DemoApplication {

	private WebClient webClient = WebClient.create();

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@GetMapping
	public Mono<String> index() {
		return webClient.get()
				.uri("http://hello-spring-k8s/name")  // Sửa lại service đúng
				.retrieve()
				.bodyToMono(String.class)
				.map(body -> "Hello " + body + " from hello-caller");
	}
}
