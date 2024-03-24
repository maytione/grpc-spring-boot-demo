package com.maytione.grpc.demo.rest;

import java.util.LinkedList;
import java.util.List;
import java.util.UUID;
import java.util.Random;

public class CarRandomProvider {
    private static final String[] CAR_MANUFACTURERS = {
            "Volkswagen", "Mercedes-Benz", "BMW", "Audi", "Renault",
            "Peugeot", "Fiat", "Volvo", "Skoda", "Land Rover"
    };
    private static final Random RANDOM = new Random();
    private static final List<Car> restCarList = new LinkedList<>();

    static {

        for (int i = 0; i < 20; i++) {
            Car car = new Car();
            car.setManufacturer(randomManufacturer());
            car.setModel(randomModel());
            restCarList.add(car);
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
        return restCarList;
    }

}
