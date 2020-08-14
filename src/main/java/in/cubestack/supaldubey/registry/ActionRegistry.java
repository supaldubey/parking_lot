package in.cubestack.supaldubey.registry;

import in.cubestack.supaldubey.action.ParkingAction;

public interface ActionRegistry {
    void register(ParkingAction parkingAction);
    ParkingAction getActionFor(String actionType);

    boolean hasActions();
}
