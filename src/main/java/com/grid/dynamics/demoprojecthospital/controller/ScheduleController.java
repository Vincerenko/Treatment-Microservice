package com.grid.dynamics.demoprojecthospital.controller;

import com.grid.dynamics.demoprojecthospital.exceptions.ApiRequestExceptionTreatment;
import com.grid.dynamics.demoprojecthospital.models.Schedule;
import com.grid.dynamics.demoprojecthospital.models.enums.UserRole;
import com.grid.dynamics.demoprojecthospital.services.AuthService;
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
    private final String wrongVerification = "Sorry, but you dont have access to this page";
    private final String occurException = "Oops... Something is going wrong.";
    private final ScheduleService scheduleService;
    private final AuthService authService;

    /**
     * @param schedule received object schedule and save
     */
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Schedule created"),
            @ApiResponse(responseCode = "404", description = "Something is going wrong")})
    @Operation(summary = "Save schedule to DB", description = "Create and write object schedule to DB")

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/schedules")
    public void saveNewSchedule(@RequestBody Schedule schedule) {
        if (authService.verifyRole(UserRole.DOCTOR, UserRole.ADMIN)) {
            try {
                scheduleService.createNewSchedule(schedule);
            } catch (RuntimeException e) {
                throw new ApiRequestExceptionTreatment(occurException);
            }
        }
        else {
            throw new ApiRequestExceptionTreatment(wrongVerification);
        }
    }

    /**
     * @param id - it's id of schedule
     * @return - return assigned schedule for patient
     */
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Schedule created"),
            @ApiResponse(responseCode = "404", description = "Something is going wrong")})
    @Operation(summary = "Get schedule from DB", description = "Get schedule by patientID")

    @GetMapping("/schedules/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Schedule getSchedule(@PathVariable("id") Long id) {
        if (authService.verifyRole(UserRole.DOCTOR, UserRole.ADMIN,UserRole.PATIENT)) {
            try {
                return scheduleService.getScheduleByPatientId(id);
            } catch (RuntimeException e) {
                throw new ApiRequestExceptionTreatment(occurException);
            }
        }
        else {
            throw new ApiRequestExceptionTreatment(wrongVerification);
        }
    }

    /**
     *
     * @param scheduleId receive id of schedule
     */
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Schedule created"),
            @ApiResponse(responseCode = "404", description = "Something is going wrong")})
    @Operation(summary = "Get schedule from DB", description = "Get schedule by patientID")

    @DeleteMapping("/schedules/{scheduleId}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteSchedule(@PathVariable("scheduleId") Long scheduleId){
        if (authService.verifyRole(UserRole.DOCTOR, UserRole.ADMIN)) {
            scheduleService.deleteSchedule(scheduleId);
        }
        else {
            throw new ApiRequestExceptionTreatment(wrongVerification);
        }
    }


}
