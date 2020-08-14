package in.cubestack.supaldubey.registry;

import in.cubestack.supaldubey.action.ParkingAction;
import in.cubestack.supaldubey.domain.ParkingLot;
import org.junit.Assert;
import org.junit.Test;

public class RegistryTest {

    @Test
    public void testSetup() {
        ActionRegistry actionRegistry = new DefaultInMemoryRegistry();
        actionRegistry.register(new ParkingAction() {
            @Override
            public String getAction() {
                return "TEST";
            }

            @Override
            public String perform(ParkingLot parkingLot, String... arguments) {
                return null;
            }

        });

        ParkingAction parkingAction = actionRegistry.getActionFor("TEST");
        Assert.assertNotNull(parkingAction);
    }

}
