package com.grid.dynamics.demoprojecthospital.controller;

import com.grid.dynamics.demoprojecthospital.models.ReceptionMedicine;
import com.grid.dynamics.demoprojecthospital.services.ReceptionMedicinesService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class ReceptionMedicinesControllerTest {
    @Mock
    private ReceptionMedicinesService receptionMedicinesService;
    @InjectMocks
    private ReceptionMedicinesController receptionMedicinesController;

    @Test
    void saveReceptionMedicines() {
        ReceptionMedicine receptionMedicine = new ReceptionMedicine();
        Mockito.doNothing().when(receptionMedicinesService).saveReceptionMedicines(1L, receptionMedicine);
        receptionMedicinesController.saveReceptionMedicines(1L, receptionMedicine);
    }

    @Test
    void deleteReceptionMedicineById() {
        Mockito.doNothing().when(receptionMedicinesService).deleteReceptionMedicine(1L);
        receptionMedicinesController.deleteReceptionMedicineById(1L);
    }
}