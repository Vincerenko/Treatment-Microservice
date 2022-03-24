package com.grid.dynamics.demoprojecthospital.controller;

import com.grid.dynamics.demoprojecthospital.exceptions.ApiRequestExceptionTreatment;
import com.grid.dynamics.demoprojecthospital.models.Appointment;
import com.grid.dynamics.demoprojecthospital.models.TreatmentEntity;
import com.grid.dynamics.demoprojecthospital.models.enums.UserRole;
import com.grid.dynamics.demoprojecthospital.repository.TreatmentRepository;
import com.grid.dynamics.demoprojecthospital.services.AppointmentService;
import com.grid.dynamics.demoprojecthospital.services.AuthService;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
class AppointmentControllerTest {
    @Mock
    private AppointmentService appointmentService;
    @Mock
    private AuthService authService;
    @InjectMocks
    private AppointmentController appointmentController;

    @Test
    void saveNeAppointment() {
        Mockito.when(authService.verifyRole(UserRole.DOCTOR, UserRole.ADMIN)).thenReturn(true);
        Mockito.doNothing().when(appointmentService).saveAppointment(new Appointment(), 1L);
        appointmentController.saveNewAppointment(new Appointment(), 1L);
    }

    @Test
    void checkThrowException() throws ApiRequestExceptionTreatment{
        Mockito.when(authService.verifyRole(UserRole.DOCTOR, UserRole.ADMIN)).thenReturn(true);
        Mockito.doNothing().when(appointmentService).saveAppointment(new Appointment(), 1L);
        assertThrows(ApiRequestExceptionTreatment.class, () ->
         appointmentController.saveNewAppointment(null,1l));

    }
}