package com.grid.dynamics.demoprojecthospital.services;

import com.grid.dynamics.demoprojecthospital.models.Medicine;
import com.grid.dynamics.demoprojecthospital.models.TreatmentEntity;
import com.grid.dynamics.demoprojecthospital.models.wrapper.MedicineDto;
import com.grid.dynamics.demoprojecthospital.repository.MedicineRepository;
import com.grid.dynamics.demoprojecthospital.repository.TreatmentRepository;
import com.grid.dynamics.demoprojecthospital.services.api.MedicineServiceApi;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MedicineService {
    private final MedicineRepository medicineRepository;
    private final TreatmentRepository treatmentRepository;
    private final MedicineServiceApi medicineServiceApi;

    public void saveCustomMedicine(Medicine medicine, Long treatmentId) {
        TreatmentEntity treatmentEntity = new TreatmentEntity();
        treatmentEntity.setId(treatmentId);
        medicine.setTreatment(treatmentEntity);
        medicineRepository.save(medicine);
        treatmentRepository.updateTreatmentPriceById(medicine.getPrice() * medicine.getCount(), treatmentId);
    }

    public void saveArrayMedicine(List<Medicine> medicine, Long treatmentId) {
        TreatmentEntity treatmentEntity = new TreatmentEntity();
        treatmentEntity.setId(treatmentId);
        for (int i = 0; i < medicine.size(); i++) {
            Medicine medicineForSave = medicine.get(i);
            medicineForSave.setTreatment(treatmentEntity);
            medicineRepository.save(medicineForSave);
            treatmentRepository.updateTreatmentPriceById(medicine.get(i).getPrice() * medicine.get(i).getCount(), treatmentId);
        }
    }

    public void saveByMedicineIdAndTreatmentId(Long treatmentId, Long medicineId) {
        MedicineDto medicineDto = medicineServiceApi.getMedicineById(medicineId);
        Medicine medicine = new Medicine(medicineDto);
        if (medicineRepository.existsByOtherId(medicineId)) {
            medicineRepository.updateMedicineCount(medicine.getCount(), medicineId);
            treatmentRepository.updateTreatmentPriceById(medicine.getPrice() * medicine.getCount(), treatmentId);

        } else {
            TreatmentEntity treatmentEntity = new TreatmentEntity();
            treatmentEntity.setId(treatmentId);
            medicine.setTreatment(treatmentEntity);
            medicineRepository.save(medicine);
            treatmentRepository.updateTreatmentPriceById(medicine.getPrice() * medicine.getCount(), treatmentId);
        }
    }
}
