package com.grid.dynamics.demoprojecthospital.services;

import com.grid.dynamics.demoprojecthospital.models.Schedule;
import com.grid.dynamics.demoprojecthospital.repository.ScheduleRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ScheduleServiceTest {
    @Mock
    private ScheduleRepository scheduleRepository;
    @InjectMocks
    private ScheduleService scheduleService;

    @Test
    @DisplayName("Should create new object schedule")
    void shouldCreateNewSchedule() {
        Schedule schedule = new Schedule(12L, 13L, 14L, new ArrayList<>());
        scheduleRepository.save(schedule);
        when(scheduleRepository.save(schedule)).thenReturn(schedule);
        scheduleService.createNewSchedule(schedule);
    }

    @Test
    @DisplayName("Should find object schedule by patient id")
    void shouldFindScheduleByPatientId() {
        Schedule schedule = new Schedule(12L, 13L, 14L, new ArrayList<>());
        scheduleService.createNewSchedule(schedule);
        when(scheduleService.getScheduleByPatientId(13L)).thenReturn(schedule);
        assertEquals(schedule, scheduleService.getScheduleByPatientId(13L));
    }
}