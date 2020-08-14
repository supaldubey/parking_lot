package in.cubestack.supaldubey.action;

import in.cubestack.supaldubey.domain.ActionType;
import in.cubestack.supaldubey.domain.ParkingLot;
import in.cubestack.supaldubey.registry.ActionRegistry;

public abstract class AbstractParkingAction implements ParkingAction {

    private final ActionType actionType;

    public AbstractParkingAction(ActionType actionType, ActionRegistry actionRegistry) {
        this.actionType = actionType;
        actionRegistry.register(this);
    }

    void assertParkingPresent(ParkingLot parkingLot) {
        if (parkingLot == null) {
            throw new RuntimeException("No Parking lot created");
        }
    }

    void assertParkingLotNotEmpty(ParkingLot parkingLot) {
        if (parkingLot == null) {
            throw new RuntimeException("No Parking lot created");
        }
        if(parkingLot.isEmpty()) {
            throw new RuntimeException("No cars parked yet");
        }
    }

    public String getAction() {
        return actionType.getActionName();
    }
}
