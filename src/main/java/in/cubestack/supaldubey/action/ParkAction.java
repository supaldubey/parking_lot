package in.cubestack.supaldubey.action;

import in.cubestack.supaldubey.domain.ActionType;
import in.cubestack.supaldubey.domain.Car;
import in.cubestack.supaldubey.domain.ParkingLot;
import in.cubestack.supaldubey.domain.Vehicle;
import in.cubestack.supaldubey.registry.ActionRegistry;

import java.util.Arrays;

import static in.cubestack.supaldubey.Assertions.require;

public class ParkAction extends AbstractParkingAction {

    public ParkAction(ActionRegistry actionRegistry) {
        super(ActionType.PARK, actionRegistry);
    }

    @Override
    public String perform(ParkingLot parkingLot, String... arguments) {
        require(arguments != null && arguments.length == 3, "Invalid Arguments for park action. Provided : " + Arrays.toString(arguments));
        assertParkingPresent(parkingLot);

        String registrationNumber = arguments[1];
        String color = arguments[2];
        Vehicle vehicle = new Car(registrationNumber, color);

        int slotLocation = parkingLot.park(vehicle);

        return String.format("Allocated slot number: %d", slotLocation);
    }
}
