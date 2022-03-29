package com.grid.dynamics.demoprojecthospital.services;

import com.grid.dynamics.demoprojecthospital.models.Appointment;
import com.grid.dynamics.demoprojecthospital.models.TreatmentEntity;
import com.grid.dynamics.demoprojecthospital.repository.AppointmentRepository;
import com.grid.dynamics.demoprojecthospital.repository.TreatmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AppointmentService {
    private final AppointmentRepository appointmentRepository;
    private final TreatmentRepository treatmentRepository;

    public void saveAppointment(Appointment appointment, Long treatmentId) {
        TreatmentEntity treatmentEntity = new TreatmentEntity();
        treatmentEntity.setId(treatmentId);
        appointment.setTreatment(treatmentEntity);
        appointmentRepository.save(appointment);
        treatmentRepository.updateTreatmentPriceById(appointment.getPrice() * appointment.getAmount(), treatmentId);
    }

}
