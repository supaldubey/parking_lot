package in.cubestack.supaldubey.action;

import in.cubestack.supaldubey.domain.ActionType;
import in.cubestack.supaldubey.domain.ParkingLot;
import in.cubestack.supaldubey.domain.ParkingSlot;
import in.cubestack.supaldubey.registry.ActionRegistry;

import java.util.Arrays;
import java.util.stream.Collectors;

import static in.cubestack.supaldubey.Assertions.require;

public class ColorLookupForRegistrationAction extends AbstractParkingAction {

    public ColorLookupForRegistrationAction(ActionRegistry actionRegistry) {
        super(ActionType.REGISTRATION_LOOKUP_COLOR, actionRegistry);
    }

    @Override
    public String perform(ParkingLot parkingLot, String... arguments) {
        require(arguments != null && arguments.length == 2, "Invalid Arguments for color lookup action. Provided : " + Arrays.toString(arguments));
        assertParkingLotNotEmpty(parkingLot);

        String color = arguments[1];
        return parkingLot.getParkingSlots().stream()
                .filter(ParkingSlot::isOccupied)
                .filter(parkingSlot -> parkingSlot.getParkedVehicle().getColor().equals(color))
                .map(ps -> ps.getParkedVehicle().getRegistrationNumber())
                .collect(Collectors.joining(", "));
    }
}
