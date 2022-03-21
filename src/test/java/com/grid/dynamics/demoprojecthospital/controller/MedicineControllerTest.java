package com.grid.dynamics.demoprojecthospital.controller;

import com.grid.dynamics.demoprojecthospital.exceptions.ApiRequestExceptionTreatment;
import com.grid.dynamics.demoprojecthospital.models.Medicine;
import com.grid.dynamics.demoprojecthospital.services.MedicineService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
class MedicineControllerTest {
    @Mock
    private MedicineService medicineService;
    @InjectMocks
    private MedicineController medicineController;

    @Test
    void ShouldNewAppointment() {
        Mockito.doNothing().when(medicineService).saveCustomMedicine(new Medicine(), 1L);
        medicineController.saveNewMedicine(new Medicine(), 1L);
    }

    @Test
    void checkThrowException() throws ApiRequestExceptionTreatment {
        Mockito.doNothing().when(medicineService).saveCustomMedicine(new Medicine(), 1L);
        Assertions.assertThrows(ApiRequestExceptionTreatment.class, () ->
            medicineController.saveNewMedicine(null,1L));
    }
}