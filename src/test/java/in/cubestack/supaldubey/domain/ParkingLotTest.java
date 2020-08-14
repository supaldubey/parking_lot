package in.cubestack.supaldubey.domain;

import org.junit.Assert;
import org.junit.Test;

import java.util.stream.IntStream;

public class ParkingLotTest {

    @Test
    public void testCapacity() {
        ParkingLot parkingLot = new ParkingLot(7);
        Assert.assertEquals(parkingLot.getMaxCapacity(), 7);
    }

    @Test
    public void testSlotsAvailable() {
        ParkingLot parkingLot = new ParkingLot(7);
        Assert.assertNotNull(parkingLot.getParkingSlots());
        Assert.assertTrue(parkingLot.isEmpty());
    }


    @Test(expected = RuntimeException.class)
    public void testParkMissingVehicle() {
        ParkingLot parkingLot = new ParkingLot(7);
        parkingLot.park(null);
    }

    @Test
    public void testParkVehicle() {
        ParkingLot parkingLot = new ParkingLot(7);
        parkingLot.park(new Car("REG", "Red"));

        Assert.assertTrue(parkingLot.getParkingSlots().stream().anyMatch(ps -> !ps.isEmpty()));
    }


    @Test
    public void testParkVehicleLocation() {
        ParkingLot parkingLot = new ParkingLot(7);
        int location = parkingLot.park(new Car("REG", "Red"));

        Assert.assertTrue(parkingLot.getParkingSlots().stream().anyMatch(ps -> !ps.isEmpty()));
        Assert.assertEquals(location, 1);

        location = parkingLot.park(new Car("REG 2", "White"));

        Assert.assertEquals(location, 2);
    }

    @Test
    public void testParkVehicleOverflow() {
        try {
            ParkingLot parkingLot = new ParkingLot(7);
            IntStream.range(0, 10)
                    .forEach(i -> parkingLot.park(new Car("REG", "Red")));
            Assert.fail();
        } catch (RuntimeException re) {
            Assert.assertEquals(re.getMessage(), "Sorry, parking lot is full");
        }
    }

    @Test
    public void testLeave() {
        ParkingLot parkingLot = new ParkingLot(1);
        int slotId = parkingLot.park(new Car("KA-01-HH-1234", "White"));

        parkingLot.leave(slotId);

        Assert.assertTrue(parkingLot.isEmpty());
    }


    @Test
    public void testParkingBounds() {
        try {
            ParkingLot parkingLot = new ParkingLot(2);
            parkingLot.park(new Car("KA-01-HH-1234", "White"));
            parkingLot.leave(4);
            Assert.fail();
        } catch (Exception ex) {
            Assert.assertEquals("Invalid slotId, out of range. Provided: 4", ex.getMessage());
        }
    }

    @Test
    public void testLeaveUnparkedLocation() {
        try {
            ParkingLot parkingLot = new ParkingLot(12);
            parkingLot.park(new Car("KA-01-HH-1234", "White"));
            parkingLot.leave(4);
            Assert.fail();
        } catch (Exception ex) {
            Assert.assertEquals("No cars parked on slot 4", ex.getMessage());
        }
    }

}
