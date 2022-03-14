package com.grid.dynamics.demoprojecthospital.services;

import com.grid.dynamics.demoprojecthospital.models.ReceptionMedicine;
import com.grid.dynamics.demoprojecthospital.models.Schedule;
import com.grid.dynamics.demoprojecthospital.repository.ReceptionMedicineRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReceptionMedicinesService {
    private final ReceptionMedicineRepository receptionMedicineRepository;

    public void saveReceptionMedicines(Long scheduleId, ReceptionMedicine receptionMedicine) {
        Schedule schedule = new Schedule();
        schedule.setId(scheduleId);
        receptionMedicine.setSchedule(schedule);
        receptionMedicineRepository.save(receptionMedicine);
    }

    public void deleteReceptionMedicine(Long receptionMedicineId) {
        receptionMedicineRepository.deleteById(receptionMedicineId);
    }
}
