package com.maytione.grpc.demo.grpc;


import com.maytione.grpc.demo.Car;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

public class CarRandomProvider {
    private static final String[] CAR_MANUFACTURERS = {
            "Volkswagen", "Mercedes-Benz", "BMW", "Audi", "Renault",
            "Peugeot", "Fiat", "Volvo", "Skoda", "Land Rover"
    };
    private static final Random RANDOM = new Random();
    private static final List<Car> grpcCarList = new LinkedList<>();

    static {
        for (int i = 0; i < 20; i++) {
            Car.Builder builder = Car.newBuilder()
                    .setManufacturer(randomManufacturer())
                    .setModel(randomModel());
            grpcCarList.add(builder.build());
        }
    }

    private static String randomModel() {
        return UUID.randomUUID().toString();
    }

    private static String randomManufacturer() {
        int index = RANDOM.nextInt(CAR_MANUFACTURERS.length);
        return CAR_MANUFACTURERS[index];
    }

    public static List<Car> getCars() {
        return grpcCarList;
    }

}
