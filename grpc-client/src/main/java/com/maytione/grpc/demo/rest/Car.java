package com.maytione.grpc.demo.rest;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Car {
    private String Model;
    private String Manufacturer;
    private String Vin;
}