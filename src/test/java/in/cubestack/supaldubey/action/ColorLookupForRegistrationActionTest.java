package in.cubestack.supaldubey.action;

import in.cubestack.supaldubey.domain.Car;
import in.cubestack.supaldubey.domain.ParkingLot;
import in.cubestack.supaldubey.registry.ActionRegistry;
import in.cubestack.supaldubey.registry.DefaultInMemoryRegistry;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ColorLookupForRegistrationActionTest {

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
            ColorLookupForRegistrationAction colorLookupForRegistrationAction = new ColorLookupForRegistrationAction(actionRegistry);
            colorLookupForRegistrationAction.perform(parkingLot, "registration_numbers_for_cars_with_colour");

            Assert.fail();
        } catch (RuntimeException ex) {
            Assert.assertEquals("Invalid Arguments for color lookup action. Provided : [registration_numbers_for_cars_with_colour]", ex.getMessage());
        }
    }


    @Test
    public void testLookUpEmpty() {
        try {
            ColorLookupForRegistrationAction colorLookupForRegistrationAction = new ColorLookupForRegistrationAction(actionRegistry);
            colorLookupForRegistrationAction.perform(parkingLot, "registration_numbers_for_cars_with_colour", "White");

            Assert.fail();
        } catch (RuntimeException ex) {
            Assert.assertEquals("No cars parked yet", ex.getMessage());
        }
    }


    @Test
    public void testLookUp() {
        parkingLot.park(new Car("REG", "White"));
        ColorLookupForRegistrationAction colorLookupForRegistrationAction = new ColorLookupForRegistrationAction(actionRegistry);
        String response = colorLookupForRegistrationAction.perform(parkingLot, "registration_numbers_for_cars_with_colour", "White");

        Assert.assertEquals(response, "REG");
    }

    @Test
    public void testLookUpMultiple() {
        parkingLot.park(new Car("REG", "White"));
        parkingLot.park(new Car("REG2", "White"));
        parkingLot.park(new Car("REGX", "Red"));
        ColorLookupForRegistrationAction colorLookupForRegistrationAction = new ColorLookupForRegistrationAction(actionRegistry);
        String response = colorLookupForRegistrationAction.perform(parkingLot, "registration_numbers_for_cars_with_colour", "White");

        Assert.assertEquals(response, "REG, REG2");
    }
}
