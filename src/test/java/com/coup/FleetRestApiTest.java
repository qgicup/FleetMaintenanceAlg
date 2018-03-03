package com.coup;

import com.coup.restapi.dto.FleetEngineerRequestDTO;
import com.coup.service.FleetService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static com.coup.service.FleetService.MAX_NO_VEHICLE_MANAGED_BY_FE;
import static com.coup.service.FleetService.MIN_NO_VEHICLE_MANAGED_BY_FE;
import static com.coup.service.FleetService.MIN_NO_VEHICLE_MANAGED_BY_FM;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.log;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static org.hamcrest.Matchers.*;

/**
 *
 * ! Important note! All these tests are mostly checking the error messages returned.
 * TODO - return specific application error codes (!= application codeS), so we can better check for these errros (especially important if the API is returning the content in multiple languages)
 *
 * Created with ♥ by georgeplaton on 03.03.18.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class FleetRestApiTest {

    private static final String API_FLEET_CALCULATION = "/calculateFleetEngineers";

    @Autowired
    private MockMvc mvc;
    @Autowired private ObjectMapper mapper;

    @Test
    public void getHello() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("Hello from FleetManagement API!")));
    }

    @Test
    public void testGivenExample1() throws Exception {
        int vehicles[] = {15, 10};
        int vehiclesMaintainedByFM = 12;
        int vehiclesMaintainedByFE = 5;

        FleetEngineerRequestDTO dto = new FleetEngineerRequestDTO(vehicles, vehiclesMaintainedByFM, vehiclesMaintainedByFE);

        mvc.perform(MockMvcRequestBuilders.post(API_FLEET_CALCULATION).content(mapper.writeValueAsBytes(dto)).contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("fleet_engineers", is(3)));
    }

    @Test
    public void testGivenExample2() throws Exception {
        int vehicles[] = {11, 15, 13};
        int vehiclesMaintainedByFM = 9;
        int vehiclesMaintainedByFE = 5;

        FleetEngineerRequestDTO dto = new FleetEngineerRequestDTO(vehicles, vehiclesMaintainedByFM, vehiclesMaintainedByFE);

        mvc.perform(MockMvcRequestBuilders.post(API_FLEET_CALCULATION).content(mapper.writeValueAsBytes(dto)).contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("fleet_engineers", is(7)));
    }

    @Test
    public void testInvalidVehiclesError() throws Exception {
        int vehicles[] = {11, 15, 13};
        int vehiclesMaintainedByFM = 9;
        int vehiclesMaintainedByFE = 5;
        FleetEngineerRequestDTO dto = new FleetEngineerRequestDTO(vehicles, vehiclesMaintainedByFM, vehiclesMaintainedByFE);

        // 1. Test invalid vehicles conditions
        int vehicles2[] = {};
        dto.setScooters(vehicles2);

        mvc.perform(MockMvcRequestBuilders.post(API_FLEET_CALCULATION).content(mapper.writeValueAsBytes(dto)).contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(content().string(containsString("Number of districts has to be between")));
    }

    @Test
    public void testInvalidVehiclesManagedByFMError() throws Exception {
        int vehicles[] = {11, 15, 13};
        int vehiclesMaintainedByFM = MIN_NO_VEHICLE_MANAGED_BY_FM - 1;
        int vehiclesMaintainedByFE = MIN_NO_VEHICLE_MANAGED_BY_FE + 1;
        FleetEngineerRequestDTO dto = new FleetEngineerRequestDTO(vehicles, vehiclesMaintainedByFM, vehiclesMaintainedByFE);

        // 1. Test invalid vehicles conditions
        dto.setVehiclesManagedByFM(MIN_NO_VEHICLE_MANAGED_BY_FM - 1);
        mvc.perform(MockMvcRequestBuilders.post(API_FLEET_CALCULATION).content(mapper.writeValueAsBytes(dto)).contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(content().string(containsString("Number of vehicles managed by Fleet Manager has to be between")));


        dto.setVehiclesManagedByFM(FleetService.MAX_NO_VEHICLE_MANAGED_BY_FM + 1);
        mvc.perform(MockMvcRequestBuilders.post(API_FLEET_CALCULATION).content(mapper.writeValueAsBytes(dto)).contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(content().string(containsString("Number of vehicles managed by Fleet Manager has to be between")));

    }

    @Test
    public void testInvalidVehiclesManagedByFEError() throws Exception {
        int vehicles[] = {11, 15, 13};
        int vehiclesMaintainedByFM = MIN_NO_VEHICLE_MANAGED_BY_FM + 1;
        int vehiclesMaintainedByFE = MIN_NO_VEHICLE_MANAGED_BY_FE - 1;
        FleetEngineerRequestDTO dto = new FleetEngineerRequestDTO(vehicles, vehiclesMaintainedByFM, vehiclesMaintainedByFE);

        // 1. Test invalid vehicles conditions
        dto.setVehiclesManagedByFE(MIN_NO_VEHICLE_MANAGED_BY_FE - 1);
        mvc.perform(MockMvcRequestBuilders.post(API_FLEET_CALCULATION).content(mapper.writeValueAsBytes(dto)).contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(content().string(containsString("Number of vehicles managed by Fleet Engineer has to be between")));


        dto.setVehiclesManagedByFE(FleetService.MAX_NO_VEHICLE_MANAGED_BY_FE + 1);
        mvc.perform(MockMvcRequestBuilders.post(API_FLEET_CALCULATION).content(mapper.writeValueAsBytes(dto)).contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(content().string(containsString("Number of vehicles managed by Fleet Engineer has to be between")));

    }

    @Test
    public void testInvalidVehiclesPerDistrictError() throws Exception {
        int vehicles[] = {11, -3, 3};
        int vehiclesMaintainedByFM = MIN_NO_VEHICLE_MANAGED_BY_FM + 1;
        int vehiclesMaintainedByFE = MIN_NO_VEHICLE_MANAGED_BY_FE + 1;
        FleetEngineerRequestDTO dto = new FleetEngineerRequestDTO(vehicles, vehiclesMaintainedByFM, vehiclesMaintainedByFE);

        // 1. Test invalid vehicles conditions
        mvc.perform(MockMvcRequestBuilders.post(API_FLEET_CALCULATION).content(mapper.writeValueAsBytes(dto)).contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(content().string(containsString("Number of vehicles in one district has to be between")));

    }
}
