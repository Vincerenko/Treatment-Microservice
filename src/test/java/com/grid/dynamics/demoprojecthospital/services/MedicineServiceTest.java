package com.grid.dynamics.demoprojecthospital.services;

import com.grid.dynamics.demoprojecthospital.models.Medicine;
import com.grid.dynamics.demoprojecthospital.models.TreatmentEntity;
import com.grid.dynamics.demoprojecthospital.repository.MedicineRepository;
import com.grid.dynamics.demoprojecthospital.repository.TreatmentRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MedicineServiceTest {
    @Mock
    private MedicineRepository medicineRepository;
    @Mock
    private TreatmentRepository treatmentRepository;
    @InjectMocks
    private MedicineService medicineService;

    @Test
    void ShouldSaveMedicine() {
        TreatmentEntity treatmentEntity = new TreatmentEntity();
        Medicine medicine = new Medicine();
        treatmentEntity.setId(1L);
        medicine.setTreatment(treatmentEntity);
        medicine.setPrice(100.0);
        medicine.setCount(2);
        when(medicineRepository.save(medicine)).thenReturn(medicine);
        doNothing().when(treatmentRepository).updateTreatmentPriceById(medicine.getPrice() * medicine.getCount(), treatmentEntity.getId());
        medicineService.saveCustomMedicine(medicine, treatmentEntity.getId());

    }
}