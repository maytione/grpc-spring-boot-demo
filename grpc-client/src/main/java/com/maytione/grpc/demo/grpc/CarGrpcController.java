package com.maytione.grpc.demo.grpc;

import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.util.JsonFormat;
import com.maytione.grpc.demo.CarList;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/test_grpc")
public class CarGrpcController {
    private final CarServiceGrpcClient carServiceGrpcClient;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> test() {

        CarList cars = carServiceGrpcClient.createCars(CarRandomProvider.getCars());
        try {
            String jsonString = JsonFormat.printer().print(cars);
            return ResponseEntity.ok(jsonString);
        } catch (InvalidProtocolBufferException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error converting protobuf to JSON");
        }
    }
}
