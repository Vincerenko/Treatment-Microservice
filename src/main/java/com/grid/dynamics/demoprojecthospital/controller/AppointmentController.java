package com.grid.dynamics.demoprojecthospital.controller;

import com.grid.dynamics.demoprojecthospital.exceptions.ApiRequestExceptionTreatment;
import com.grid.dynamics.demoprojecthospital.models.Appointment;
import com.grid.dynamics.demoprojecthospital.models.enums.UserRole;
import com.grid.dynamics.demoprojecthospital.services.AppointmentService;
import com.grid.dynamics.demoprojecthospital.services.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "api/v1")
@RequiredArgsConstructor
public class AppointmentController {
    private final String noSave = "Something is going wrong, medicine wasn't saved.";
    private final String wrongVerification = "Sorry, but you dont have access to this page";
    private final AuthService authService;
    private final AppointmentService appointmentService;

    /**
     *
     * @param appointment receive json object appointment
     * @param treatmentId receive id of treatment that already exists in database and will be connected with json object appointment
     */
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "appointment created"),
            @ApiResponse(responseCode = "404", description = "appointment didn't create")
    })
    @Operation(summary = "Save appointment", description = "Save appointment and calc sum which will be connected to treatment bu treatment_id")

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/appointments/{treatmentId}")
    public void saveNewAppointment(@RequestBody Appointment appointment, @PathVariable(name = "treatmentId") Long treatmentId) {
        if (authService.verifyRole(UserRole.DOCTOR, UserRole.ADMIN)) {
            try {
                appointmentService.saveAppointment(appointment, treatmentId);
            } catch (RuntimeException e) {
                throw new ApiRequestExceptionTreatment(noSave);
            }
        } else {
            throw new ApiRequestExceptionTreatment(wrongVerification);
        }

    }

}
