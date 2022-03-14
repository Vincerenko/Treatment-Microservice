package com.grid.dynamics.demoprojecthospital.services;

import com.grid.dynamics.demoprojecthospital.models.ReceptionMedicine;
import com.grid.dynamics.demoprojecthospital.models.Schedule;
import com.grid.dynamics.demoprojecthospital.repository.ReceptionMedicineRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ReceptionMedicinesServiceTest {
    @Mock
    private ReceptionMedicineRepository receptionMedicineRepository;
    @InjectMocks
    private ReceptionMedicinesService receptionMedicinesService;

    @Test
    void ShouldSaveReceptionMedicines() {
        ReceptionMedicine receptionMedicine = new ReceptionMedicine();
        Schedule schedule = new Schedule();
        schedule.setId(1L);
        receptionMedicine.setSchedule(schedule);
        when(receptionMedicineRepository.save(receptionMedicine)).thenReturn(receptionMedicine);
        receptionMedicinesService.saveReceptionMedicines(schedule.getId(), receptionMedicine);
    }

    @Test
    void ShouldDeleteReceptionMedicine() {
        doNothing().when(receptionMedicineRepository).deleteById(1L);
        receptionMedicinesService.deleteReceptionMedicine(1L);
    }
}