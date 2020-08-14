package in.cubestack.supaldubey;

import in.cubestack.supaldubey.action.*;
import in.cubestack.supaldubey.domain.CarTest;
import in.cubestack.supaldubey.domain.ParkingLotTest;
import in.cubestack.supaldubey.domain.ParkingSlotTest;
import in.cubestack.supaldubey.executor.ActionExecutorTest;
import in.cubestack.supaldubey.registry.RegistryTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@Suite.SuiteClasses(value = {
        CarTest.class,
        ParkingLotTest.class,
        ParkingSlotTest.class,
        RegistryTest.class,
        ParkActionTest.class,
        LeaveParkingActionTest.class,
        ParkingStatusActionTest.class,
        ColorLookupForRegistrationActionTest.class,
        SlotNumberByColorActionTest.class,
        RegistrationNumberLookupActionTest.class,
        ActionExecutorTest.class
})
@RunWith(Suite.class)
public class AllTest {
}
