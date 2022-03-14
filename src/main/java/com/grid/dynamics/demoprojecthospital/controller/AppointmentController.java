package com.grid.dynamics.demoprojecthospital.controller;

import com.grid.dynamics.demoprojecthospital.exceptions.ApiRequestExceptionTreatment;
import com.grid.dynamics.demoprojecthospital.models.Appointment;
import com.grid.dynamics.demoprojecthospital.repository.TreatmentRepository;
import com.grid.dynamics.demoprojecthospital.services.AppointmentService;
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
    private final AppointmentService appointmentService;
    private final TreatmentRepository treatmentRepository;

    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "appointment created"),
            @ApiResponse(responseCode = "404", description = "appointment didn't create")
    })
    @Operation(summary = "create appointment", description = "create appointment and calc sum which will be connected to treatment bu treatment_id")
    @PostMapping("/appointments/{treatmentId}")
    @ResponseStatus(HttpStatus.CREATED)
    public void saveNewAppointment(@RequestBody Appointment appointment, @PathVariable(name = "treatmentId") Long treatmentId) throws ApiRequestExceptionTreatment{
        try {
            appointmentService.saveAppointment(appointment, treatmentId);
        } catch (RuntimeException e) {
            throw new ApiRequestExceptionTreatment("Something is going wrong, appointment wasn't saved, maybe your appointment.treatment_id doesn't match with main treatment.id");
        }

    }

}
