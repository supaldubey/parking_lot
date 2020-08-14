package in.cubestack.supaldubey.domain;

public class Car extends Vehicle {

    public Car(String registrationNumber, String color) {
        super(registrationNumber, VehicleType.CAR, color);
    }
}
