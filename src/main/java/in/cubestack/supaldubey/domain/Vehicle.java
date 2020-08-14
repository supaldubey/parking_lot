package in.cubestack.supaldubey.domain;

public abstract class Vehicle {

    private String registrationNumber;
    private VehicleType vehicleType;
    private final String color;

    Vehicle(String registrationNumber, VehicleType vehicleType, String color) {
        this.registrationNumber = registrationNumber;
        this.vehicleType = vehicleType;
        this.color = color;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public VehicleType getVehicleType() {
        return vehicleType;
    }

    public String getColor() {
        return color;
    }

}
