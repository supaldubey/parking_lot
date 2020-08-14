package in.cubestack.supaldubey.action;

import in.cubestack.supaldubey.domain.ParkingLot;
import in.cubestack.supaldubey.registry.DefaultInMemoryRegistry;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.stream.IntStream;

public class ParkActionTest {

    private ParkingLot parkingLot;

    @Before
    public void init() {
        parkingLot = new ParkingLot(10);
    }

    @Test
    public void testPark() {
        ParkAction parkAction = new ParkAction(new DefaultInMemoryRegistry());
        Assert.assertTrue(parkingLot.isEmpty());
        String actionOutput = parkAction.perform(parkingLot, "park", "KA-01-HH-1234", "White");
        Assert.assertFalse(parkingLot.isEmpty());

        Assert.assertEquals(actionOutput, "Allocated slot number: 1");
    }

    @Test(expected = RuntimeException.class)
    public void testParkOverflow() {
        ParkAction parkAction = new ParkAction(new DefaultInMemoryRegistry());
        Assert.assertTrue(parkingLot.isEmpty());

        IntStream.range(0, 11).forEach( index -> parkAction.perform(parkingLot, "park", "KA-01-HH-1234", "White"));

        Assert.fail();
    }
}
