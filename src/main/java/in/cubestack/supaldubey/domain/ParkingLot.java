package in.cubestack.supaldubey.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

import static in.cubestack.supaldubey.Assertions.require;
import static in.cubestack.supaldubey.Assertions.requireNotNull;

public class ParkingLot {

    private final int maxCapacity;
    private final List<ParkingSlot> parkingSlots;

    public ParkingLot(int maxCapacity) {
        this.maxCapacity = maxCapacity;
        this.parkingSlots = new ArrayList<>(maxCapacity);
        initializeParkingSlots();
    }

    private void initializeParkingSlots() {
        IntStream.range(0, maxCapacity)
                .forEach(i -> parkingSlots.add(new ParkingSlot(i + 1)));
    }

    public int getMaxCapacity() {
        return maxCapacity;
    }

    public List<ParkingSlot> getParkingSlots() {
        return Collections.unmodifiableList(parkingSlots);
    }

    public int park(Vehicle vehicle) {
        requireNotNull(vehicle, "No vehicle provided for parking");

        ParkingSlot parkingSlot = findNextAvailableSlot()
                .orElseThrow(() -> new RuntimeException("Sorry, parking lot is full"));
        parkingSlot.park(vehicle);

        return parkingSlots.indexOf(parkingSlot) + 1;
    }

    public String leave(int slotId) {
        require(slotId > 0 && slotId <= maxCapacity + 1, "Invalid slotId, out of range. Provided: " + slotId);
        ParkingSlot parkingSlot = parkingSlots.get(slotId - 1);
        parkingSlot.unpark();
        return String.format("Slot number %s is free", slotId);
    }

    private Optional<ParkingSlot> findNextAvailableSlot() {
        return parkingSlots.stream()
                .filter(ParkingSlot::isEmpty).findFirst();
    }

    public boolean isEmpty() {
        return parkingSlots.stream().allMatch(ParkingSlot::isEmpty);
    }
}
