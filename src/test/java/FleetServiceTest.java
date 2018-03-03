import static org.junit.Assert.*;
import org.junit.*;

/**
 * Created with ♥ by georgeplaton on 03.03.18.
 */
public class FleetServiceTest {


    /**
     * Will treat the example 1 given in the problem
     * input: { scooters: [15, 10], C: 12, P:5 }
     * expected output: { fleet_engineers: 3 }
     */
    @Test
    public void testCaseGivenExample1() {
        int vehicles[] = {15, 10};
        int vehiclesMaintainedByFM = 12;
        int vehiclesMaintainedByFE = 5;

        FleetService fleetService = new FleetService();
        try {
            Assert.assertEquals(3, fleetService.calculateMinFE(vehicles, vehiclesMaintainedByFM, vehiclesMaintainedByFE));
        } catch (FleetException e) {
            Assert.fail("Test should not have failed. Exception :" +  e.getMessage());
        }
    }

    /**
     * Will treat the example 2 given in the problem
     * input: { scooters: [11, 15, 13], C: 9, P:5 }
     * expected output: { fleet_engineers: 7 }
     */
    @Test
    public void testCaseGivenExample2() {
        int vehicles[] = {11, 15, 13};
        int vehiclesMaintainedByFM = 9;
        int vehiclesMaintainedByFE = 5;

        FleetService fleetService = new FleetService();
        try {
            Assert.assertEquals(7, fleetService.calculateMinFE(vehicles, vehiclesMaintainedByFM, vehiclesMaintainedByFE));
        } catch (FleetException e) {
            Assert.fail("Test should not have failed. Exception :" +  e.getMessage());
        }
    }

    /**
     * This test will test with a large district, each having vehicles bigger and lower than the tasks FM or FE can handle
     */
    @Test
    public void testCaseWithLargeDistrict() {
        int vehicles[] = {11, 15, 13, 14, 12, 13, 15, 12, 16, 2, 1, 2, 4, 5, 4, 9, 10, 30};
        int vehiclesMaintainedByFM = 10;
        int vehiclesMaintainedByFE = 6;

        FleetService fleetService = new FleetService();
        try {
            Assert.assertEquals(37, fleetService.calculateMinFE(vehicles, vehiclesMaintainedByFM, vehiclesMaintainedByFE));
        } catch (FleetException e) {
            Assert.fail("Test should not have failed. Exception :" +  e.getMessage());
        }
    }
}
