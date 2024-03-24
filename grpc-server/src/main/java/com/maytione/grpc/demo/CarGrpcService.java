package com.maytione.grpc.demo;

import io.grpc.Status;
import io.grpc.stub.StreamObserver;
import lombok.extern.slf4j.Slf4j;
import org.lognet.springboot.grpc.GRpcService;

@Slf4j
@GRpcService
public class CarGrpcService extends CarServiceGrpc.CarServiceImplBase {

    /**
     * Implementation of the createCars RPC method.
     * Generates VIN numbers for the received cars and sends the updated car list back to the client.
     *
     * @param request           The request containing a list of cars.
     * @param responseObserver The observer for sending the response back to the client.
     */
    @Override
    public void createCars(CarList request, StreamObserver<CarList> responseObserver) {
        log.debug("Client requested: {}", request);

        try {
            // Generate VIN numbers for the cars in the request
            CarList.Builder responseBuilder = CarList.newBuilder();
            CarUtil.assignVinNumber(request.getCarList()).forEach(responseBuilder::addCar);
            CarList carListResponse = responseBuilder.build();

            log.debug("Response from server: {}", carListResponse);

            // Send the updated car list back to the client
            responseObserver.onNext(carListResponse);
            responseObserver.onCompleted();
        } catch (Exception e) {
            log.error("Error processing request", e);
            responseObserver.onError(Status.INTERNAL.withDescription("Internal server error").asRuntimeException());
        }
    }
}