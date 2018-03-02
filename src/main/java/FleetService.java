

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
     * That means optimizing the number of vehicles a FM can solve in a certain district, so there is at least as possible work left for other FE in the same district.
     *
     * E.g  vehicles [11, 15, 13], vehiclesMaintainedByFM : 9, vehiclesMaintainedByFE: 5
     *      It's best if FM works in district vehicles[0] which has 11 vehicles.
     *      vehicles[0] district: 11 - 9 (1 FM) = 2 vehicles left to be managed (requires 1 FE).
     *      vehicles[1] district: 15 - 9 (1 FM) = 6 vehicles left to be managed (requires 2 FE).
     *      vehicles[2] district: 13 - 9 (1 FM) = 4 vehicles left to be managed (requires 1 FE).
     *
     *      From the above you can see that the best option for FM is to go in district 0 or 2
     *
     * Aim :
     *      - find the min number of FE correctly
     *      - calculate in O(n)
     *
     * @param vehicles                      - array of districts. Each element of the array denotes the number of vehicles inside that district.
     *                                        Each vehicle number must follow (MIN_NO_VEHICLE_IN_DISTRICT < vehicle number < MAX_NO_VEHICLE_IN_DISTRICT)
     * @param vehiclesMaintainedByFM        - the number of vehicles one fleet manager (FM) can maintain
     * @param vehiclesMaintainedByFE        - the number of vehicles one fleet engineer (FE) can maintain
     * @return                              - minim number of fleet engineers (FE) needed to handle all the given vehicles in the various districts
     *                                        throws error in case given input is not valid.
     */
    public int calculateMinFE(final int[] vehicles, final int vehiclesMaintainedByFM, final int vehiclesMaintainedByFE) {

        // TODO validate input here

        // TODO find the district with the min number of left vehicles to be managed, after FM works on them

        // TODO calculate all other districts (number of FE needed)

        return 0;
    }
}
