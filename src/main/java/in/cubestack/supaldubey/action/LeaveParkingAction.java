package in.cubestack.supaldubey.action;

import in.cubestack.supaldubey.domain.ActionType;
import in.cubestack.supaldubey.domain.ParkingLot;
import in.cubestack.supaldubey.registry.ActionRegistry;

import java.util.Arrays;

import static in.cubestack.supaldubey.Assertions.require;

public class LeaveParkingAction extends AbstractParkingAction {

    public LeaveParkingAction(ActionRegistry actionRegistry) {
        super(ActionType.LEAVE, actionRegistry);
    }

    @Override
    public String perform(ParkingLot parkingLot, String... arguments) {
        require(arguments != null && arguments.length == 2, "Invalid Arguments for leave action. Provided : " + Arrays.toString(arguments));
        // Assuming arguments to be "leave 1"
        int slotId = Integer.parseInt(arguments[1]);
        return parkingLot.leave(slotId);
    }
}
