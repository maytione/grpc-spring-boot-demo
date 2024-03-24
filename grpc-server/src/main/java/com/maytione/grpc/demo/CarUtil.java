package com.maytione.grpc.demo;

import java.util.LinkedList;
import java.util.List;

public class CarUtil {

    private static final String ALPHA_NUMERIC_STRING = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

    public static List<Car> assignVinNumber(List<Car> cars) {

        List<Car> result = new LinkedList<>();
        for (Car car : cars) {
            Car newCar = Car.newBuilder(car)
                    .setVin(generateVin())
                    .build();
            result.add(newCar);
        }
        return result;
    }

    public static String generateVin() {
        int length = 17;
        StringBuilder builder = new StringBuilder();
        while (length-- != 0) {
            int character = (int) (Math.random() * ALPHA_NUMERIC_STRING.length());
            builder.append(ALPHA_NUMERIC_STRING.charAt(character));
        }
        return builder.toString();
    }
}
