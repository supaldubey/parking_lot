package in.cubestack.supaldubey.action;

import in.cubestack.supaldubey.domain.Car;
import in.cubestack.supaldubey.domain.ParkingLot;
import in.cubestack.supaldubey.registry.ActionRegistry;
import in.cubestack.supaldubey.registry.DefaultInMemoryRegistry;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class RegistrationNumberLookupActionTest {
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
            RegistrationNumberLookupAction registrationNumberLookupAction = new RegistrationNumberLookupAction(actionRegistry);
            registrationNumberLookupAction.perform(parkingLot, "slot_number_for_registration_number");

            Assert.fail();
        } catch (RuntimeException ex) {
            Assert.assertEquals("Invalid Arguments for slot by registration lookup action. Provided : [slot_number_for_registration_number]", ex.getMessage());
        }
    }


    @Test
    public void testLookUpEmpty() {
        try {
            RegistrationNumberLookupAction registrationNumberLookupAction = new RegistrationNumberLookupAction(actionRegistry);
            registrationNumberLookupAction.perform(parkingLot, "slot_number_for_registration_number", "REG");

            Assert.fail();
        } catch (RuntimeException ex) {
            Assert.assertEquals("No cars parked yet", ex.getMessage());
        }
    }


    @Test
    public void testLookUp() {
        parkingLot.park(new Car("REG", "White"));
        RegistrationNumberLookupAction registrationNumberLookupAction = new RegistrationNumberLookupAction(actionRegistry);
        String response = registrationNumberLookupAction.perform(parkingLot, "slot_number_for_registration_number", "REG");

        Assert.assertEquals(response, "1");
    }

    @Test
    public void testLookUpNotFound() {
        parkingLot.park(new Car("REG", "White"));
        parkingLot.park(new Car("REG2", "White"));
        parkingLot.park(new Car("REGX", "Red"));
        RegistrationNumberLookupAction registrationNumberLookupAction = new RegistrationNumberLookupAction(actionRegistry);
        String response = registrationNumberLookupAction.perform(parkingLot, "slot_number_for_registration_number", "XYZ");

        Assert.assertEquals(response, "Not found");
    }
}
