package in.cubestack.supaldubey.action;

import in.cubestack.supaldubey.domain.ParkingLot;

public interface ParkingAction {
    String getAction();

    String perform(ParkingLot parkingLot, String... arguments);
}
