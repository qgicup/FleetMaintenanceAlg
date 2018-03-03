import exception.FleetException;
import exception.FleetExceptionType;
import org.junit.*;
import org.junit.rules.ExpectedException;

import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.startsWith;

/**
 * Created with ♥ by georgeplaton on 03.03.18.
 */
public class FleetServiceTest {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();


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

    /**
     * Will test districts that have less scouters than the capacity of FM or FE.
     * In this manner, it will be complicated to decide where FM will get involved, which can potentially cause problems, if FM does not get involved anywhere.
     */
    @Test
    public void testCaseWithVehiclesLessThanFMOrFECapacity() {
        int vehicles[] = {1, 4, 3};
        int vehiclesMaintainedByFM = 9;
        int vehiclesMaintainedByFE = 5;

        FleetService fleetService = new FleetService();
        try {
            Assert.assertEquals(2, fleetService.calculateMinFE(vehicles, vehiclesMaintainedByFM, vehiclesMaintainedByFE));
        } catch (FleetException e) {
            Assert.fail("Test should not have failed. Exception :" +  e.getMessage());
        }
    }

    /**
     * Will test districts that have 0 vehicles inside.
     * For these districts, there should be no FE or FM involved.
     */
    @Test
    public void testCaseDistrictsWithZeroVehicles() {
        int vehicles[] = {0, 0, 0, 1, 4, 3, 0, 0, 0, 0, 0};
        int vehiclesMaintainedByFM = 9;
        int vehiclesMaintainedByFE = 5;

        FleetService fleetService = new FleetService();
        try {
            Assert.assertEquals(2, fleetService.calculateMinFE(vehicles, vehiclesMaintainedByFM, vehiclesMaintainedByFE));
        } catch (FleetException e) {
            Assert.fail("Test should not have failed. Exception :" +  e.getMessage());
        }
    }

    /**
     * Will test the validation of vehicles data.
     */
    @Test
    public void testCaseValidationOfInputVehiclesData() throws FleetException {

        // 1. No vehicles minimum
        int vehicles[] = {};
        int vehiclesMaintainedByFM = 9;
        int vehiclesMaintainedByFE = 5;

        FleetService fleetService = new FleetService();
        expectedException.expect(FleetException.class);
        expectedException.expect(hasProperty("exceptionType", is(FleetExceptionType.INVALID_NUM_DISTRICTS)));
        Assert.assertEquals(2, fleetService.calculateMinFE(vehicles, vehiclesMaintainedByFM, vehiclesMaintainedByFE));
    }



}
