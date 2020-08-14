package in.cubestack.supaldubey.domain;

import org.junit.Assert;
import org.junit.Test;

public class CarTest {

    @Test
    public void testBasic() {
        Car car = new Car("KA-01-HH-1234", "White");
        Assert.assertEquals(car.getVehicleType(), VehicleType.CAR);
        Assert.assertEquals(car.getRegistrationNumber(), "KA-01-HH-1234");
    }
}
