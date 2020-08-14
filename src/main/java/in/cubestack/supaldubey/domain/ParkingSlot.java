package in.cubestack.supaldubey.domain;

import in.cubestack.supaldubey.Assertions;

import java.util.Objects;

import static java.lang.String.format;

public class ParkingSlot {

    private final int slotId;
    private Vehicle vehicle;

    ParkingSlot(int slotId) {
        Assertions.require(slotId > 0, "Invalid slot id, only positive slot ids accepted. Provided: " + slotId);
        this.slotId = slotId;
    }

    public int getSlotId() {
        return slotId;
    }

    public Vehicle getParkedVehicle() {
        return vehicle;
    }

    public void park(Vehicle car) {
        if (this.vehicle != null) {
            throw new RuntimeException(format("Car %s is already parked", car));
        }
        this.vehicle = car;
    }

    public void unpark() {
        if (this.vehicle == null) {
            throw new RuntimeException("No cars parked on slot " + slotId);
        }
        this.vehicle = null;
    }

    public boolean isEmpty() {
        return vehicle == null;
    }

    public boolean isOccupied() {
        return !isEmpty();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ParkingSlot that = (ParkingSlot) o;
        return slotId == that.slotId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(slotId);
    }
}
