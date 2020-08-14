package in.cubestack.supaldubey.registry;

import in.cubestack.supaldubey.action.ParkingAction;

import java.util.HashMap;
import java.util.Map;

public class DefaultInMemoryRegistry implements ActionRegistry {

    private final Map<String, ParkingAction> ACTION_REGISTRY_MAP;

    public DefaultInMemoryRegistry() {
        ACTION_REGISTRY_MAP = new HashMap<>();
    }

    @Override
    public void register(ParkingAction parkingAction) {
        ACTION_REGISTRY_MAP.put(parkingAction.getAction(), parkingAction);
    }

    @Override
    public ParkingAction getActionFor(String actionType) {
        return ACTION_REGISTRY_MAP.get(actionType);
    }

    @Override
    public boolean hasActions() {
        return !ACTION_REGISTRY_MAP.isEmpty();
    }
}
