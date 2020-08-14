package in.cubestack.supaldubey.domain;

import org.junit.Assert;
import org.junit.Test;

public class ParkingSlotTest {

    @Test
    public void testSetup() {
        ParkingSlot parkingSlot = new ParkingSlot(1);
        Assert.assertEquals(parkingSlot.getSlotId(), 1);
        Assert.assertNull(parkingSlot.getParkedVehicle());
    }


    @Test
    public void testSlotAssignment() {
        ParkingSlot parkingSlot = new ParkingSlot(1);
        Assert.assertNull(parkingSlot.getParkedVehicle());

        Car car = new Car("KA-01-HH-1234", "White");
        parkingSlot.park(car);

        Assert.assertNotNull(parkingSlot.getParkedVehicle());
        Assert.assertEquals(parkingSlot.getParkedVehicle(), car);
    }

    @Test(expected = RuntimeException.class)
    public void testAssignedSlotAssignment() {
        ParkingSlot parkingSlot = new ParkingSlot(1);

        Car car = new Car("KA-01-HH-1234", "White");
        parkingSlot.park(car);
        Assert.assertNotNull(parkingSlot.getParkedVehicle());

        parkingSlot.park(new Car("XYZ", "White"));
    }

    @Test
    public void testSlotUnAssignment() {
        ParkingSlot parkingSlot = new ParkingSlot(1);
        Assert.assertNull(parkingSlot.getParkedVehicle());

        Car car = new Car("KA-01-HH-1234", "White");
        parkingSlot.park(car);

        Assert.assertNotNull(parkingSlot.getParkedVehicle());
        Assert.assertEquals(parkingSlot.getParkedVehicle(), car);

        parkingSlot.unpark();
        Assert.assertNull(parkingSlot.getParkedVehicle());
    }

    @Test(expected = RuntimeException.class)
    public void testInvalidSlot() {
        new ParkingSlot(-1);

        Assert.fail();
    }

}
