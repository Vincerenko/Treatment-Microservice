package com.grid.dynamics.demoprojecthospital.dto;

import com.grid.dynamics.demoprojecthospital.models.Appointment;
import com.grid.dynamics.demoprojecthospital.models.Medicine;
import com.grid.dynamics.demoprojecthospital.models.TreatmentEntity;
import com.grid.dynamics.demoprojecthospital.models.enums.Status;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Set;

/**
 * This DTO used in ordinal logic, to simple work with this DTO.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(hidden = true)
public class TreatmentDto {

    private Long id;
    private Status status;
    private String title;
    private Long doctorId;
    private Long patientId;
    private String room;
    private String currency;
    private Double price;
    private LocalDate startDate;
    private Long duringDay;
    private LocalDate endDate;
    private Long appointmentId;
    private String description;
    private Set<Medicine> medicine;
    private Set<Appointment> appointment;

    public TreatmentDto(TreatmentEntity treatmentEntity) {
        this.id = treatmentEntity.getId();
        this.status = treatmentEntity.getStatus();
        this.title = treatmentEntity.getTitle();
        this.doctorId = treatmentEntity.getDoctorId();
        this.patientId = treatmentEntity.getPatientId();
        this.room = treatmentEntity.getRoom();
        this.currency = treatmentEntity.getCurrency();
        this.price = treatmentEntity.getPrice();
        this.startDate = treatmentEntity.getStartDate();
        this.duringDay = treatmentEntity.getDuringDay();
        this.endDate = treatmentEntity.getEndDate();
        this.appointmentId = treatmentEntity.getAppointmentId();
        this.description = treatmentEntity.getDescription();
        this.medicine = treatmentEntity.getMedicine();
        this.appointment = treatmentEntity.getAppointment();
    }
}