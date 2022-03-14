package com.grid.dynamics.demoprojecthospital.controller;

import com.grid.dynamics.demoprojecthospital.models.ReceptionMedicine;
import com.grid.dynamics.demoprojecthospital.models.Schedule;
import com.grid.dynamics.demoprojecthospital.services.ReceptionMedicinesService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "api/v1")
@RequiredArgsConstructor
public class ReceptionMedicinesController {

    private final ReceptionMedicinesService receptionMedicinesService;

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Schedule created"),
            @ApiResponse(responseCode = "404", description = "Something is going wrong")})
    @Operation(summary = "post reception to DB", description = "create and write object reception to DB")
    @PostMapping("/receptions/{scheduleId}")
    @ResponseStatus(HttpStatus.CREATED)
    public void saveReceptionMedicines(@PathVariable("scheduleId") Long scheduleId, @RequestBody ReceptionMedicine receptionMedicine) {
        Schedule schedule = new Schedule();
        schedule.setId(scheduleId);
        receptionMedicine.setSchedule(schedule);
        receptionMedicinesService.saveReceptionMedicines(scheduleId, receptionMedicine);
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Schedule created"),
            @ApiResponse(responseCode = "404", description = "Something is going wrong")})
    @Operation(summary = "delete reception to DB", description = "delete reception to DB")
    @DeleteMapping("/receptions/{receptionMedicineId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteReceptionMedicineById(@PathVariable("receptionMedicineId") Long receptionMedicineId) {
        receptionMedicinesService.deleteReceptionMedicine(receptionMedicineId);
    }
}
