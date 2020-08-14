package in.cubestack.supaldubey.action;

import in.cubestack.supaldubey.domain.Car;
import in.cubestack.supaldubey.domain.ParkingLot;
import in.cubestack.supaldubey.registry.ActionRegistry;
import in.cubestack.supaldubey.registry.DefaultInMemoryRegistry;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class LeaveParkingActionTest {

    private ActionRegistry actionRegistry;
    private ParkingLot parkingLot;

    @Before
    public void init() {
        actionRegistry = new DefaultInMemoryRegistry();
        parkingLot = new ParkingLot(10);
    }

    @Test
    public void testInvalidArgs() {
        try {
            LeaveParkingAction leaveParkingAction = new LeaveParkingAction(actionRegistry);
            leaveParkingAction.perform(parkingLot, "leave");

            Assert.fail();
        } catch (RuntimeException ex) {
            Assert.assertEquals("Invalid Arguments for leave action. Provided : [leave]", ex.getMessage());
        }
    }


    @Test
    public void testLeaveParkingNothingParked() {
        try {
            LeaveParkingAction leaveParkingAction = new LeaveParkingAction(actionRegistry);
            leaveParkingAction.perform(parkingLot, "leave", "1");

            Assert.fail();
        } catch (RuntimeException ex) {
            Assert.assertEquals("No cars parked on slot 1", ex.getMessage());
        }
    }


    @Test
    public void testLeaveParking() {

        int slotId = parkingLot.park(new Car("REG", "COLO"));
        LeaveParkingAction leaveParkingAction = new LeaveParkingAction(actionRegistry);
        String response = leaveParkingAction.perform(parkingLot, "leave", String.valueOf(slotId));

        Assert.assertEquals(response, String.format("Slot number %s is free", slotId));
    }
}
