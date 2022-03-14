package com.grid.dynamics.demoprojecthospital.services;

import com.grid.dynamics.demoprojecthospital.models.Medicine;
import com.grid.dynamics.demoprojecthospital.models.TreatmentEntity;
import com.grid.dynamics.demoprojecthospital.repository.MedicineRepository;
import com.grid.dynamics.demoprojecthospital.repository.TreatmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MedicineService {
    private final MedicineRepository medicineRepository;
    private final TreatmentRepository treatmentRepository;

    public void saveMedicine(Medicine medicine, Long treatmentId) {
        TreatmentEntity treatmentEntity = new TreatmentEntity();
        treatmentEntity.setId(treatmentId);
        medicine.setTreatment(treatmentEntity);
        medicineRepository.save(medicine);
        treatmentRepository.updateTreatmentPriceById(medicine.getPrice() * medicine.getCount(), treatmentId);
    }

}
