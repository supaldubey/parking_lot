package in.cubestack.supaldubey.action;

import in.cubestack.supaldubey.domain.ActionType;
import in.cubestack.supaldubey.domain.ParkingLot;
import in.cubestack.supaldubey.domain.ParkingSlot;
import in.cubestack.supaldubey.registry.ActionRegistry;

import java.util.Arrays;

import static in.cubestack.supaldubey.Assertions.require;

public class ParkingStatusAction extends AbstractParkingAction {

    public ParkingStatusAction(ActionRegistry actionRegistry) {
        super(ActionType.STATUS, actionRegistry);
    }

    @Override
    public String perform(ParkingLot parkingLot, String... arguments) {
        require(arguments != null && arguments.length == 1, "Invalid Arguments for status action. Provided : " + Arrays.toString(arguments));
        assertParkingLotNotEmpty(parkingLot);

        final StringBuilder response = new StringBuilder("Slot No.\tRegistration No\tColour\n");
        parkingLot.getParkingSlots().stream().filter(ParkingSlot::isOccupied)
                .forEach(ps -> appendDetailsForSlot(ps, response));

        return response.toString();
    }

    private void appendDetailsForSlot(ParkingSlot parkingSlot, StringBuilder response) {
        response.append(parkingSlot.getSlotId())
                .append("\t")
                .append(parkingSlot.getParkedVehicle().getRegistrationNumber())
                .append("\t")
                .append(parkingSlot.getParkedVehicle().getColor())
                .append("\n");
    }
}
