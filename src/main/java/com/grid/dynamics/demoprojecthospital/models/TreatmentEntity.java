package com.grid.dynamics.demoprojecthospital.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.grid.dynamics.demoprojecthospital.dto.TreatmentSaveDto;
import com.grid.dynamics.demoprojecthospital.models.enums.Status;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Set;

/**
 * This class show main Entity of Treatment.
 */
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
@Table(name = "treatments")
public class TreatmentEntity implements Serializable {

    @Id
    @SequenceGenerator(
            name = "treatment_sequence",
            sequenceName = "treatment_sequence",
            allocationSize = 1
    )
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "treatment_sequence"
    )
    @Column(name = "treatment_id")
    @JsonProperty
    private Long id;
    @JsonProperty
    @Column(name = "status", nullable = false)
    @NonNull
    @Enumerated(EnumType.STRING)
    private Status status;
    @JsonProperty
    @Column(name = "title", nullable = false)
    @NonNull
    private String title;
    @Column(name = "doctor_id", nullable = false)
    @JsonProperty(namespace = "doctor_id")
    @NonNull
    private Long doctorId;
    @Column(name = "patient_id", nullable = false)
    @JsonProperty(namespace = "patient_id")
    @NonNull
    private Long patientId;
    @JsonProperty
    @Column(name = "room", nullable = false)
    @NonNull
    private String room;
    @JsonProperty
    @Column(name = "price", nullable = false)
    @NonNull
    private Double price;
    @JsonProperty
    @Column(name = "currency", nullable = false)
    @NonNull
    private String currency;
    @Column(name = "start_date", nullable = false)
    @JsonProperty(namespace = "start_date")
    @NonNull
    private LocalDate startDate;
    @Column(name = "end_date", nullable = false)
    @JsonProperty(namespace = "end_date")
    @NonNull
    private LocalDate endDate;
    @Column(name = "duration_days", nullable = false)
    @JsonProperty(namespace = "duration_days")
    @NonNull
    private Long duringDay;
    @Column(name = "appointment_id", nullable = false)
    @JsonProperty(namespace = "appointment_id")
    @NonNull
    private Long appointmentId;
    @JsonProperty
    @Column(name = "description", nullable = false)
    @NonNull
    private String description;
    @OneToMany(mappedBy = "treatment", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonProperty(namespace = "medicines")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<Medicine> medicine;
    @OneToMany(mappedBy = "treatment", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonProperty(namespace = "appointments")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<Appointment> appointment;

    public TreatmentEntity(TreatmentSaveDto treatmentSaveDto) {
        Long duringDay = treatmentSaveDto.getDuringDay();
        LocalDate firstPeriod = LocalDate.now(ZoneId.of("UTC"));
        LocalDate secondPeriod = LocalDate.now(ZoneId.of("UTC")).plusDays(duringDay);
        this.status = treatmentSaveDto.getStatus();
        this.title = treatmentSaveDto.getTitle();
        this.doctorId = treatmentSaveDto.getDoctorId();
        this.patientId = treatmentSaveDto.getPatientId();
        this.room = treatmentSaveDto.getRoom();
        this.currency = treatmentSaveDto.getCurrency();
        this.price = treatmentSaveDto.getPrice();
        this.startDate = firstPeriod;
        this.endDate = secondPeriod;
        this.duringDay = duringDay;
        this.appointmentId = treatmentSaveDto.getAppointmentId();
        this.description = treatmentSaveDto.getDescription();
        this.medicine = treatmentSaveDto.getMedicine();
        this.appointment = treatmentSaveDto.getAppointment();
    }


}
