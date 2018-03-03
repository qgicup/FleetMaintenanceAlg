package com.coup.restapi.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

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

    @JsonProperty("fleet_engineers")
    public int getNumFleetEngineers() {
        return numFleetEngineers;
    }

    @JsonProperty("fleet_engineers")
    public void setNumFleetEngineers(int numFleetEngineers) {
        this.numFleetEngineers = numFleetEngineers;
    }

    @Override
    public String toString() {
        return "FleetEngineerResponseDTO{" + "numFleetEngineers=" + numFleetEngineers + '}';
    }
}
