package com.employeeManagement.webclient.WebclientDemoProject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.annotation.PostConstruct;

@SpringBootApplication
@RestController
public class WebclientDemoProjectApplication {

	WebClient webClient;

	@PostConstruct
	public void init() {
		webClient = WebClient.builder().baseUrl("http://localhost:8182")
				.defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE).build();
	}

	@PostMapping("/addEmployee")
	public Mono<Employees> addEmployee(@RequestBody Employees request) {
		return webClient.post().uri("/addEmployee").syncBody(request).retrieve().bodyToMono(Employees.class);
	}

	@GetMapping("/getAllEmployees")
	public Flux<Employees> getAllEmployees() {
		return
				webClient.get().uri("/getAllEmployees").retrieve().bodyToFlux(Employees.class);
	}

	@GetMapping("/getEmployeeById/{empId}")
	public Mono<Employees> getEmployeeById(@PathVariable int empId) {
		return webClient.get().uri("/getEmployeeById/" + empId).retrieve().bodyToMono(Employees.class);
	}

	@DeleteMapping("/deleteEmployee/{empId}")
	public Mono<String> deleteEmployee(@PathVariable int empId) {
		return webClient.delete().uri("/deleteEmployee/" + empId).retrieve().bodyToMono(String.class);
	}

	@PutMapping("/updateEmployee")
	public Mono<Employees> updateEmployee(@RequestBody Employees request) {
		return webClient.put().uri("/updateEmployee").syncBody(request).retrieve()
				.bodyToMono(Employees.class);
	}

	public static void main(String[] args) {
		SpringApplication.run(WebclientDemoProjectApplication.class, args);
	}

}
