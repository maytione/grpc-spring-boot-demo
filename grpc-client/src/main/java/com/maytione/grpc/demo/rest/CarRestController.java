package com.maytione.grpc.demo.rest;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
public class CarRestController {

    private final RestTemplate restTemplate;

    public CarRestController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @GetMapping("/test_rest")
    public ResponseEntity<List<Car>> testRest() {
        HttpEntity<List<Car>> requestEntity = new HttpEntity<>(CarRandomProvider.getCars());

        try {
            ResponseEntity<List<Car>> responseEntity = restTemplate.exchange(
                    "http://localhost:8081/cars/create",
                    HttpMethod.POST,
                    requestEntity,
                    new ParameterizedTypeReference<>() {}
            );
            return ResponseEntity.ok(responseEntity.getBody());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
