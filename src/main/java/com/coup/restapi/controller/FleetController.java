package com.coup.restapi.controller;

import com.coup.exception.FleetException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.coup.restapi.dto.FleetEngineerRequestDTO;
import com.coup.restapi.dto.FleetEngineerResponseDTO;
import com.coup.service.FleetService;

/**
 *
 * This is the main Web REST Api endpoint which is gonna serve the requests to the service.
 *
 * Created with ♥ by georgeplaton on 02.03.18.
 */
@RestController
public class FleetController {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    FleetService fleetService;

    @RequestMapping("/")
    public String index() {
        return "Hello from FleetManagement API!";
    }

    @RequestMapping(value = "/calculateFleetEngineers", method = RequestMethod.GET)
    public @ResponseBody FleetEngineerResponseDTO calculateFleetEngineers(@RequestBody FleetEngineerRequestDTO fleetEngineerRequestDTO) {

        FleetEngineerResponseDTO responseDTO = new FleetEngineerResponseDTO();

        try {
            int noEngineers = fleetService.calculateMinFE(fleetEngineerRequestDTO.getScooters(),
                    fleetEngineerRequestDTO.getVehiclesManagedByFM(),
                    fleetEngineerRequestDTO.getVehiclesManagedByFE());

            responseDTO.setNumFleetEngineers(noEngineers);
        } catch (FleetException e) {
            log.error("Failed to calculate the number of engineers required due error.", e);
        }

        return responseDTO;
    }
}
