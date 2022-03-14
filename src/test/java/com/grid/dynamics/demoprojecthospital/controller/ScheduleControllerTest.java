package com.grid.dynamics.demoprojecthospital.controller;

import com.grid.dynamics.demoprojecthospital.models.Schedule;
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
    private ScheduleService scheduleService;
    @InjectMocks
    private ScheduleController scheduleController;

    @Test
    void saveNewTreatment() {
        Schedule schedule = new Schedule();
        Mockito.doNothing().when(scheduleService).createNewSchedule(schedule);
        scheduleController.saveNewTreatment(schedule);
    }

    @Test
    void getSchedule() {
        Schedule expected = new Schedule();
        expected.setPatientId(1L);
        Mockito.when(scheduleService.getScheduleByPatientId(1L)).thenReturn(expected);
        Schedule actual = scheduleController.getSchedule(1L);
        assertEquals(expected, actual);
    }
}