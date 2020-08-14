package in.cubestack.supaldubey.action;

import in.cubestack.supaldubey.domain.ParkingLot;
import in.cubestack.supaldubey.domain.ParkingSlot;
import in.cubestack.supaldubey.registry.ActionRegistry;

import java.util.Arrays;

import static in.cubestack.supaldubey.Assertions.require;
import static in.cubestack.supaldubey.domain.ActionType.SLOT_LOOKUP_REGISTRATION;

public class RegistrationNumberLookupAction extends AbstractParkingAction {

    public RegistrationNumberLookupAction(ActionRegistry actionRegistry) {
        super(SLOT_LOOKUP_REGISTRATION, actionRegistry);
    }

    @Override
    public String perform(ParkingLot parkingLot, String... arguments) {
        require(arguments != null && arguments.length == 2, "Invalid Arguments for slot by registration lookup action. Provided : " + Arrays.toString(arguments));
        assertParkingLotNotEmpty(parkingLot);

        String registrationNumber = arguments[1];
        return parkingLot.getParkingSlots().stream()
                .filter(ParkingSlot::isOccupied)
                .filter(parkingSlot -> parkingSlot.getParkedVehicle().getRegistrationNumber().equals(registrationNumber))
                .findFirst()
                .map(ParkingSlot::getSlotId)
                .map(String::valueOf)
                .orElse("Not found");
    }
}
