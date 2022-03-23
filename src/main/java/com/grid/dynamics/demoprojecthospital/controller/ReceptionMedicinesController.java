package com.grid.dynamics.demoprojecthospital.controller;

import com.grid.dynamics.demoprojecthospital.exceptions.ApiRequestExceptionTreatment;
import com.grid.dynamics.demoprojecthospital.models.ReceptionMedicine;
import com.grid.dynamics.demoprojecthospital.models.Schedule;
import com.grid.dynamics.demoprojecthospital.models.enums.UserRole;
import com.grid.dynamics.demoprojecthospital.services.AuthService;
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
    private final String wrongVerification = "Sorry, but you dont have access to this page";
    private final String occurException = "Oops... Something is going wrong.";
    private final ReceptionMedicinesService receptionMedicinesService;
    private final AuthService authService;

    /**
     * @param scheduleId        receive schedule id
     * @param receptionMedicine receive json object receptionMedicine
     */
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Schedule created"),
            @ApiResponse(responseCode = "404", description = "Something is going wrong")})
    @Operation(summary = "Save reception to DB", description = "Create and save object reception to DB")

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/receptions/{scheduleId}")
    public void saveReceptionMedicines(@PathVariable("scheduleId") Long scheduleId, @RequestBody ReceptionMedicine receptionMedicine) {
        if (authService.verifyRole(UserRole.DOCTOR, UserRole.ADMIN)) {
            try {
                Schedule schedule = new Schedule();
                schedule.setId(scheduleId);
                receptionMedicine.setSchedule(schedule);
                receptionMedicinesService.saveReceptionMedicines(scheduleId, receptionMedicine);
            } catch (RuntimeException e) {
                throw new ApiRequestExceptionTreatment(occurException);
            }
        } else {
            throw new ApiRequestExceptionTreatment(wrongVerification);
        }
    }

    /**
     * @param receptionMedicineId receive id of object ReceptionMedicine
     */
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Schedule created"),
            @ApiResponse(responseCode = "404", description = "Something is going wrong")})
    @Operation(summary = "Delete ReceptionMedicine from DB", description = "Delete ReceptionMedicine from DB by id of ReceptionMedicine")

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/receptions/{receptionMedicineId}")
    public void deleteReceptionMedicineById(@PathVariable("receptionMedicineId") Long receptionMedicineId) {
        if (authService.verifyRole(UserRole.DOCTOR, UserRole.ADMIN)) {
            try {
                receptionMedicinesService.deleteReceptionMedicine(receptionMedicineId);
            } catch (RuntimeException e) {
                throw new ApiRequestExceptionTreatment(occurException);
            }
        } else {
            throw new ApiRequestExceptionTreatment(wrongVerification);
        }

    }
}
