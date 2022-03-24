package com.grid.dynamics.demoprojecthospital.controller;

import com.grid.dynamics.demoprojecthospital.models.Schedule;
import com.grid.dynamics.demoprojecthospital.models.enums.UserRole;
import com.grid.dynamics.demoprojecthospital.services.AuthService;
import com.grid.dynamics.demoprojecthospital.services.ScheduleService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class ScheduleControllerTest {
    @Mock
    private AuthService authService;
    @Mock
    private ScheduleService scheduleService;
    @InjectMocks
    private ScheduleController scheduleController;

    @Test
    void saveNewTreatment() {
        Mockito.when(authService.verifyRole(UserRole.DOCTOR, UserRole.ADMIN)).thenReturn(true);
        Schedule schedule = new Schedule();
        Mockito.doNothing().when(scheduleService).createNewSchedule(schedule);
        scheduleController.saveNewSchedule(schedule);
    }

    @Test
    void getSchedule() {
        Mockito.when(authService.verifyRole(UserRole.DOCTOR, UserRole.ADMIN,UserRole.PATIENT)).thenReturn(true);
        Schedule expected = new Schedule();
        expected.setPatientId(1L);
        Mockito.when(scheduleService.getScheduleByPatientId(1L)).thenReturn(expected);
        Schedule actual = scheduleController.getSchedule(1L);
        assertEquals(expected, actual);
    }
}