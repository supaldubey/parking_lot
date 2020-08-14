package in.cubestack.supaldubey.action;

import in.cubestack.supaldubey.domain.Car;
import in.cubestack.supaldubey.domain.ParkingLot;
import in.cubestack.supaldubey.registry.ActionRegistry;
import in.cubestack.supaldubey.registry.DefaultInMemoryRegistry;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ParkingStatusActionTest {

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
            ParkingStatusAction parkingStatusAction = new ParkingStatusAction(actionRegistry);
            parkingStatusAction.perform(parkingLot);

            Assert.fail();
        } catch (RuntimeException ex) {
            Assert.assertEquals("Invalid Arguments for status action. Provided : []", ex.getMessage());
        }
    }

    @Test
    public void testEmptyStatus() {
        try {
            ParkingStatusAction parkingStatusAction = new ParkingStatusAction(actionRegistry);
            parkingStatusAction.perform(parkingLot, "status");

            Assert.fail();
        } catch (RuntimeException ex) {
            Assert.assertEquals("No cars parked yet", ex.getMessage());
        }
    }

    @Test
    public void testStatus() {

        ParkingStatusAction parkingStatusAction = new ParkingStatusAction(actionRegistry);
        parkingLot.park(new Car("REG", "WHITE"));

        String status = parkingStatusAction.perform(parkingLot, "status");
        Assert.assertEquals(status, "Slot No.\tRegistration No\tColour\n1\tREG\tWHITE\n");
    }

}
