package com.coup;

import com.coup.exception.FleetException;
import com.coup.service.FleetService;

import java.io.IOException;
import java.util.Scanner;

/**
 * Created with ♥ by georgeplaton on 02.03.18.
 */
public class Cli {

    public static void main(String[] args) throws IOException {

        // 1. Read the districts and the scooters inside.
        Scanner sc = new Scanner(System.in);
        System.out.println("Please enter number of districts:");
        int noDistricts = sc.nextInt();

        int[] scootersArray = new int[noDistricts];

        System.out.println("Please enter array elements:");
        for (int i = 0; i < noDistricts; i++) {
            scootersArray[i] = sc.nextInt();
        }

        // 2. Read the scooters managed by Fleet Manager
        System.out.println("Please enter the number of scooters managed by Fleet Manager:");
        int scootersHandledByFM = sc.nextInt();

        System.out.println("Please enter the number of scooters managed by Fleet Engineer:");
        int scootersHandledByFE = sc.nextInt();


        FleetService fleetService = new FleetService();
        try {
            System.out.println("{fleet_engineers : " + fleetService.calculateMinFE(scootersArray, scootersHandledByFM, scootersHandledByFE) + "}");
        } catch (FleetException e) {
            System.err.println("Failed to find the number of fleet engineers requested due error :"  + e.getMessage());
        }
    }
}
