package com.coup.restapi.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Arrays;

/**
 * Created with ♥ by georgeplaton on 03.03.18.
 */
public class FleetEngineerRequestDTO {

    private int[] scooters;
    private int vehiclesManagedByFM;
    private int vehiclesManagedByFE;

    public FleetEngineerRequestDTO() {
        scooters = null;
        vehiclesManagedByFM = 0;
        vehiclesManagedByFE = 0;
    }

    public FleetEngineerRequestDTO(int[] scooters, int vehiclesManagedByFM, int vehiclesManagedByFE) {
        this.scooters = scooters;
        this.vehiclesManagedByFM = vehiclesManagedByFM;
        this.vehiclesManagedByFE = vehiclesManagedByFE;
    }

    public int[] getScooters() {
        return scooters;
    }

    public void setScooters(int[] scooters) {
        this.scooters = scooters;
    }

    @JsonProperty("C")
    public int getVehiclesManagedByFM() {
        return vehiclesManagedByFM;
    }

    @JsonProperty("C")
    public void setVehiclesManagedByFM(int vehiclesManagedByFM) {
        this.vehiclesManagedByFM = vehiclesManagedByFM;
    }

    @JsonProperty("P")
    public int getVehiclesManagedByFE() {
        return vehiclesManagedByFE;
    }

    @JsonProperty("P")
    public void setVehiclesManagedByFE(int vehiclesManagedByFE) {
        this.vehiclesManagedByFE = vehiclesManagedByFE;
    }

    @Override
    public String toString() {
        return "FleetEngineerRequestDTO{" + "scooters=" + Arrays.toString(scooters) + ", vehiclesManagedByFM=" + vehiclesManagedByFM + ", vehiclesManagedByFE="
                + vehiclesManagedByFE + '}';
    }
}
