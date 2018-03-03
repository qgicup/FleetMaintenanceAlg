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

    // INPUT VALIDATION TESTS

    /**
     * Will test the validation of vehicles data.
     */
    @Test
    public void testCaseValidationOfInputVehiclesData() throws FleetException {

        // 1. Num vehicles minimum
        int vehicles[] = new int[FleetService.MIN_NO_DISTRICTS - 1];
        int vehiclesMaintainedByFM = 9;
        int vehiclesMaintainedByFE = 5;

        FleetService fleetService = new FleetService();
        expectedException.expect(FleetException.class);
        expectedException.expect(hasProperty("exceptionType", is(FleetExceptionType.INVALID_NUM_DISTRICTS)));
        Assert.assertEquals(0, fleetService.calculateMinFE(vehicles, vehiclesMaintainedByFM, vehiclesMaintainedByFE));

        // 2. Num vehicles max
        int vehicles2[] = new int[FleetService.MAX_NO_DISTRICTS + 1];
        Assert.assertEquals(0, fleetService.calculateMinFE(vehicles2, vehiclesMaintainedByFM, vehiclesMaintainedByFE));

    }

    /**
     * Will test the validation of number of vehicles maintained by FM.
     * They have to be between MIN_NO_VEHICLE_MANAGED_BY_FM and MAX_NO_VEHICLE_MANAGED_BY_FM
     */
    @Test
    public void testCaseValidationOfInputVehiclesManagedByFMData() throws FleetException {

        // 1. Num vehicles to maintain minimum for FM
        int vehicles[] = {11, 15, 13};
        int vehiclesMaintainedByFM = FleetService.MIN_NO_VEHICLE_MANAGED_BY_FM - 1;
        int vehiclesMaintainedByFE = FleetService.MIN_NO_VEHICLE_MANAGED_BY_FE + 1;

        FleetService fleetService = new FleetService();
        expectedException.expect(FleetException.class);
        expectedException.expect(hasProperty("exceptionType", is(FleetExceptionType.INVALID_NUM_VEHICLE_MANAGED_BY_FM)));
        Assert.assertEquals(0, fleetService.calculateMinFE(vehicles, vehiclesMaintainedByFM, vehiclesMaintainedByFE));

        // 2. Num vehicles to maintain max for FM
        vehiclesMaintainedByFM = FleetService.MAX_NO_VEHICLE_MANAGED_BY_FM + 1;

        expectedException.expect(FleetException.class);
        expectedException.expect(hasProperty("exceptionType", is(FleetExceptionType.INVALID_NUM_VEHICLE_MANAGED_BY_FM)));
        Assert.assertEquals(0, fleetService.calculateMinFE(vehicles, vehiclesMaintainedByFM, vehiclesMaintainedByFE));
    }

    /**
     * Will test the validation of number of vehicles maintained by FE.
     * They have to be between MIN_NO_VEHICLE_MANAGED_BY_FE and MAX_NO_VEHICLE_MANAGED_BY_FE
     */
    @Test
    public void testCaseValidationOfInputVehiclesManagedByFEData() throws FleetException {

        // 1. Num vehicles to maintain minimum for FE
        int vehicles[] = {11, 15, 13};
        int vehiclesMaintainedByFM = FleetService.MIN_NO_VEHICLE_MANAGED_BY_FM + 1;
        int vehiclesMaintainedByFE = FleetService.MIN_NO_VEHICLE_MANAGED_BY_FE - 1;

        FleetService fleetService = new FleetService();
        expectedException.expect(FleetException.class);
        expectedException.expect(hasProperty("exceptionType", is(FleetExceptionType.INVALID_NUM_VEHICLE_MANAGED_BY_FE)));
        Assert.assertEquals(0, fleetService.calculateMinFE(vehicles, vehiclesMaintainedByFM, vehiclesMaintainedByFE));


        // TODO this one won't test it correctly.
        // 2. Num vehicles to maintain max for FE
        vehiclesMaintainedByFE = FleetService.MAX_NO_VEHICLE_MANAGED_BY_FE + 1;

        expectedException.expect(FleetException.class);
        expectedException.expect(hasProperty("exceptionType", is(FleetExceptionType.INVALID_NUM_VEHICLE_MANAGED_BY_FE)));
        Assert.assertEquals(0, fleetService.calculateMinFE(vehicles, vehiclesMaintainedByFM, vehiclesMaintainedByFE));
    }

    /**
     * Will test the validation of number of vehicles per district.
     * They have to be between MIN_NO_VEHICLE_IN_DISTRICT and MAX_NO_VEHICLE_IN_DISTRICT
     */
    @Test
    public void testCaseValidationOfInputVehiclesPerDistrictData() throws FleetException {

        // 1. Num vehicles in district as minimum
        int vehicles[] = {-5, 10, 13};
        int vehiclesMaintainedByFM = FleetService.MIN_NO_VEHICLE_MANAGED_BY_FM + 1;
        int vehiclesMaintainedByFE = FleetService.MIN_NO_VEHICLE_MANAGED_BY_FE + 1;

        FleetService fleetService = new FleetService();
        expectedException.expect(FleetException.class);
        expectedException.expect(hasProperty("exceptionType", is(FleetExceptionType.INVALID_NUM_VEHICLE_IN_DISTRICT)));
        Assert.assertEquals(0, fleetService.calculateMinFE(vehicles, vehiclesMaintainedByFM, vehiclesMaintainedByFE));

        // 2. Num vehicles in district as maximum
        int vehicles2[] = {5, FleetService.MAX_NO_VEHICLE_IN_DISTRICT + 1, 13};
        expectedException.expect(FleetException.class);
        expectedException.expect(hasProperty("exceptionType", is(FleetExceptionType.INVALID_NUM_VEHICLE_MANAGED_BY_FE)));
        Assert.assertEquals(0, fleetService.calculateMinFE(vehicles2, vehiclesMaintainedByFM, vehiclesMaintainedByFE));
    }
}
