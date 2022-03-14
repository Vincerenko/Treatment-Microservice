package com.grid.dynamics.demoprojecthospital.controller;

import com.grid.dynamics.demoprojecthospital.models.Schedule;
import com.grid.dynamics.demoprojecthospital.services.ScheduleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "api/v1")
@RequiredArgsConstructor
public class ScheduleController {

    private final ScheduleService scheduleService;

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Schedule created"),
            @ApiResponse(responseCode = "404", description = "Something is going wrong")})
    @Operation(summary = "post schedules to DB", description = "create and write object schedule to DB")
    @PostMapping("/schedules")
    @ResponseStatus(HttpStatus.CREATED)
    public void saveNewTreatment(@RequestBody Schedule schedule) {
        scheduleService.createNewSchedule(schedule);
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Schedule created"),
            @ApiResponse(responseCode = "404", description = "Something is going wrong")})
    @Operation(summary = "get schedules from DB", description = "get schedule by patientID")
    @GetMapping("/schedules/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Schedule getSchedule(@PathVariable("id") Long id) {
        return scheduleService.getScheduleByPatientId(id);
    }


}
