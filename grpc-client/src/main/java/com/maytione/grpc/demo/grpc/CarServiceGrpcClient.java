package com.maytione.grpc.demo.grpc;

import com.maytione.grpc.demo.Car;
import com.maytione.grpc.demo.CarList;
import com.maytione.grpc.demo.CarServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PreDestroy;
import java.util.List;


public class CarServiceGrpcClient {
    private static final Logger log = LoggerFactory.getLogger(CarServiceGrpcClient.class);
    private final ManagedChannel channel;
    private final CarServiceGrpc.CarServiceBlockingStub blockingStub;

    public CarServiceGrpcClient(String host, int port) {
        this(ManagedChannelBuilder.forAddress(host, port).usePlaintext());
    }

    public CarServiceGrpcClient(ManagedChannelBuilder<?> channelBuilder) {
        this.channel = channelBuilder.build();
        this.blockingStub = CarServiceGrpc.newBlockingStub(channel);
    }

    public CarList createCars(List<Car> carList) {
        try {
            CarList.Builder builder = CarList.newBuilder();
            carList.forEach(builder::addCar);
            CarList request = builder.build();
            log.debug("Request {}", request);
            CarList response = blockingStub.createCars(request);
            log.debug("Response {}", response);
            return response;
        } catch (Exception e) {
            log.error("Error occurred during gRPC call", e);
            throw new RuntimeException("Error occurred during gRPC call", e);
        }
    }

    @PreDestroy
    public void shutdown() {
        log.debug("Shutting down gRPC channel");
        channel.shutdown();
    }
}
