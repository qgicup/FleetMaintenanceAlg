package com.coup.restapi.dto;

/**
 * Created with ♥ by georgeplaton on 03.03.18.
 */
public class FleetEngineerResponseDTO {

    int numFleetEngineers;

    public FleetEngineerResponseDTO() {
        numFleetEngineers = 0;
    }

    public FleetEngineerResponseDTO(int numFleetEngineers) {
        this.numFleetEngineers = numFleetEngineers;
    }

    public int getNumFleetEngineers() {
        return numFleetEngineers;
    }

    public void setNumFleetEngineers(int numFleetEngineers) {
        this.numFleetEngineers = numFleetEngineers;
    }

    @Override
    public String toString() {
        return "FleetEngineerResponseDTO{" + "numFleetEngineers=" + numFleetEngineers + '}';
    }
}
