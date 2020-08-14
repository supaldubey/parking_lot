package in.cubestack.supaldubey.action;

import in.cubestack.supaldubey.domain.Car;
import in.cubestack.supaldubey.domain.ParkingLot;
import in.cubestack.supaldubey.registry.ActionRegistry;
import in.cubestack.supaldubey.registry.DefaultInMemoryRegistry;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class SlotNumberByColorActionTest {

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
            SlotNumberByColorAction slotNumberByColorAction = new SlotNumberByColorAction(actionRegistry);
            slotNumberByColorAction.perform(parkingLot, "slot_numbers_for_cars_with_colour");

            Assert.fail();
        } catch (RuntimeException ex) {
            Assert.assertEquals("Invalid Arguments for slot by color lookup action. Provided : [slot_numbers_for_cars_with_colour]", ex.getMessage());
        }
    }


    @Test
    public void testLookUpEmpty() {
        try {
            SlotNumberByColorAction slotNumberByColorAction = new SlotNumberByColorAction(actionRegistry);
            slotNumberByColorAction.perform(parkingLot, "slot_numbers_for_cars_with_colour", "White");

            Assert.fail();
        } catch (RuntimeException ex) {
            Assert.assertEquals("No cars parked yet", ex.getMessage());
        }
    }


    @Test
    public void testLookUp() {
        parkingLot.park(new Car("REG", "White"));
        SlotNumberByColorAction slotNumberByColorAction = new SlotNumberByColorAction(actionRegistry);
        String response = slotNumberByColorAction.perform(parkingLot, "slot_numbers_for_cars_with_colour", "White");

        Assert.assertEquals(response, "1");
    }

    @Test
    public void testLookUpMultiple() {
        parkingLot.park(new Car("REG", "White"));
        parkingLot.park(new Car("REGX", "Red"));
        parkingLot.park(new Car("REG2", "White"));

        SlotNumberByColorAction slotNumberByColorAction = new SlotNumberByColorAction(actionRegistry);
        String response = slotNumberByColorAction.perform(parkingLot, "slot_numbers_for_cars_with_colour", "White");

        Assert.assertEquals(response, "1, 3");
    }
}
