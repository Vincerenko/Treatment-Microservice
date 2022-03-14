package com.grid.dynamics.demoprojecthospital.dto;

import com.grid.dynamics.demoprojecthospital.models.Appointment;
import com.grid.dynamics.demoprojecthospital.models.Medicine;
import com.grid.dynamics.demoprojecthospital.models.enums.Status;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

/**
 * This DTO is using when we want to save new object Treatment with current time.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(hidden = true)
public class TreatmentSaveDto {
    private Long id;
    private Status status;
    private String title;
    private Long doctorId;
    private Long patientId;
    private String room;
    private String currency;
    private Double price;
    private Long duringDay;
    private Long appointmentId;
    private String description;
    private Set<Medicine> medicine;
    private Set<Appointment> appointment;

    public TreatmentSaveDto(TreatmentDto treatmentDto) {
        this.id = treatmentDto.getId();
        this.status = treatmentDto.getStatus();
        this.title = treatmentDto.getTitle();
        this.doctorId = treatmentDto.getDoctorId();
        this.patientId = treatmentDto.getPatientId();
        this.room = treatmentDto.getRoom();
        this.currency = treatmentDto.getCurrency();
        this.price = treatmentDto.getPrice();
        this.duringDay = treatmentDto.getDuringDay();
        this.appointmentId = treatmentDto.getAppointmentId();
        this.description = treatmentDto.getDescription();
        this.medicine = treatmentDto.getMedicine();
        this.appointment = treatmentDto.getAppointment();
    }

}
