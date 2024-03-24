package com.maytione.grpc.demo.rest;

import com.maytione.grpc.demo.CarUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/cars")
public class CarController {

    @PostMapping("/create")
    public ResponseEntity<List<Car>> createCars(@RequestBody List<Car> cars) {
        cars.forEach(car -> car.setVin(CarUtil.generateVin()));
        return ResponseEntity.ok(cars);
    }
}