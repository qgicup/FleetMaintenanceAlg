import exception.FleetException;
import exception.FleetExceptionType;

/**
 *
 * This is the main service which does all fleet related logic.
 *
 * Created with ♥ by georgeplaton on 02.03.18.
 */
public class FleetService {

    // Constants for validation
    private static final int MIN_NO_DISTRICTS = 1;
    private static final int MAX_NO_DISTRICTS = 100;

    private static final int MIN_NO_VEHICLE_IN_DISTRICT = 0;
    private static final int MAX_NO_VEHICLE_IN_DISTRICT = 1000;

    private static final int MIN_NO_VEHICLE_MANAGED_BY_FM = 1;
    private static final int MAX_NO_VEHICLE_MANAGED_BY_FM = 999;

    private static final int MIN_NO_VEHICLE_MANAGED_BY_FE = 1;
    private static final int MAX_NO_VEHICLE_MANAGED_BY_FE = 1000;

    /**
     * This function will calculate the minimum number of fleet engineers (FE) required to
     * maintain scooters in various districts. Fleet engineers (FE) are managed by one fleet manager (FM).
     * Each FE, as well as the FM, can only maintain scooters in one district. Hence it is important they can fulfil their work in that district as much as possible.
     *
     * Algorithm :
     * Since we have only one Fleet Manager (FM), it's important to make sure he will be working on a district which can best benefit from his capacity of maintainig vehicles.
     * That means trying to maximize the number of vehicles a FM can work on in a certain district, so there is as least as possible work left for other FE in the same district.
     *
     * E.g  vehicles [11, 15, 13], vehiclesMaintainedByFM : 9, vehiclesMaintainedByFE: 5
     *      It's best if FM works in district vehicles[0] which has 11 vehicles.
     *      vehicles[0] district: 11 - 9 (1 FM) = 2 vehicles left to be managed (requires 1 FE).
     *      vehicles[1] district: 15 - 9 (1 FM) = 6 vehicles left to be managed (requires 2 FE).
     *      vehicles[2] district: 13 - 9 (1 FM) = 4 vehicles left to be managed (requires 1 FE).
     *
     *      From the above you can see that the best option for FM is to go in district 0 or 2
     *
     * @param vehicles                      - array of districts. Each element of the array denotes the number of vehicles inside that district.
     *                                        Each vehicle number must follow (MIN_NO_VEHICLE_IN_DISTRICT < vehicle number < MAX_NO_VEHICLE_IN_DISTRICT)
     * @param vehiclesMaintainedByFM        - the number of vehicles one fleet manager (FM) can maintain
     * @param vehiclesMaintainedByFE        - the number of vehicles one fleet engineer (FE) can maintain
     * @return                              - minim number of fleet engineers (FE) needed to handle all the given vehicles in the various districts
     *                                        throws error in case given input is not valid.
     */
    public int calculateMinFE(final int[] vehicles, final int vehiclesMaintainedByFM, final int vehiclesMaintainedByFE) throws FleetException {

        // 1. Validate the input
        if(vehicles.length < MIN_NO_DISTRICTS || vehicles.length > MAX_NO_DISTRICTS)
            throw new FleetException(FleetExceptionType.INVALID_NUM_DISTRICTS,
                                     "Number of districts has to be between [" + MIN_NO_DISTRICTS + "] and [" + MAX_NO_DISTRICTS + "]. Current value: [" + vehicles.length + "]");

        if(vehiclesMaintainedByFM < MIN_NO_VEHICLE_MANAGED_BY_FM || vehiclesMaintainedByFM > MAX_NO_VEHICLE_MANAGED_BY_FM)
            throw new FleetException(FleetExceptionType.INVALID_NUM_VEHICLE_MANAGED_BY_FM,
                                     "Number of vehicles managed by Fleet Manager has to be between [" + MIN_NO_VEHICLE_MANAGED_BY_FM + "] and [" + MAX_NO_VEHICLE_MANAGED_BY_FM + "]. Current value: [" + vehiclesMaintainedByFM + "]");

        if(vehiclesMaintainedByFE < MIN_NO_VEHICLE_MANAGED_BY_FE || vehiclesMaintainedByFE > MAX_NO_VEHICLE_MANAGED_BY_FE)
            throw new FleetException(FleetExceptionType.INVALID_NUM_VEHICLE_MANAGED_BY_FE,
                                     "Number of vehicles managed by Fleet Engineer has to be between [" + MIN_NO_VEHICLE_MANAGED_BY_FE + "] and [" + MAX_NO_VEHICLE_MANAGED_BY_FE + "]. Current value: [" + vehiclesMaintainedByFE + "]");

        int totalFE = 0;
        int bestGainWithFM = 0;

        // 2. Check the districts one by one
        for(int i = 0; i < vehicles.length; i++) {
            final int noVehicles = vehicles[i];

            // 2.1 Validate num vehicles in district
            if (noVehicles < MIN_NO_VEHICLE_IN_DISTRICT || noVehicles > MAX_NO_VEHICLE_IN_DISTRICT) {
                throw new FleetException(FleetExceptionType.INVALID_NUM_VEHICLE_IN_DISTRICT,
                                         "Number of vehicles in one district has to be between [" + MIN_NO_VEHICLE_IN_DISTRICT + "] < current value : " + noVehicles + " <  [" + MAX_NO_VEHICLE_IN_DISTRICT + "]");
            }

            // 2.2 Calculate number of FE needed here
            int noFE = divideAndRoundUp(noVehicles, vehiclesMaintainedByFE);
            totalFE += noFE;

            // 2.3 Calculate number of FE needed, in case FM get's involved
            int noFEWithFM = divideAndRoundUp(noVehicles - vehiclesMaintainedByFM, vehiclesMaintainedByFE);

            // 2.4 Calculate if it's a good district for FM to get involved, compared with past districts
            int gainWithFM = noFE - noFEWithFM;
            System.out.println("On district [" + i + "], there are [" + noVehicles + "] vehicles. We need " + noFE + " FE. Together with FM, we need " + noFEWithFM + " FE. FM brings a gain of : " + gainWithFM);

            if(gainWithFM > bestGainWithFM)
                bestGainWithFM = gainWithFM;
        }

        // From the total number of engineers needed, substract the best saving that the FM has brought.
        return totalFE - bestGainWithFM;
    }

    // UTILS

    /**
     * This function will divide the given parameters and round up if there is a remainder of the operation.
     *
     * We do a / b with always floor if a and b are both integers.
     * Then we have an inline if-statement witch checks whether or not we should ceil instead of floor.
     * So +1 or +0, if there is a remainder with the division we need +1. a % b == 0 checks for the remainder.
     *
     * @param a     - must not be negative or 0
     * @param b     - must not be negative or 0
     * @return      - the result of divide operation
     */
    private int divideAndRoundUp(int a, int b) {
        if(a < 1 || b < 1)
            return 0;

        return a / b + (a % b == 0 ? 0 : 1);
    }
}
