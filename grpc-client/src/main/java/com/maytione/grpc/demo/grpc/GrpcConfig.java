package com.maytione.grpc.demo.grpc;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GrpcConfig {
    @Bean
    public CarServiceGrpcClient carServiceGrpcClient() {
        return new CarServiceGrpcClient("localhost", 6565);
    }
}
