package com.grid.dynamics.demoprojecthospital.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.grid.dynamics.demoprojecthospital.models.wrapper.AppointmentCalendarDto;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * This class describe model of service in hospital that mapped to Data Base and this class is used in business logic
 */
@Entity(name = "appointment")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Appointment implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonProperty
    private Long id;
    @JsonProperty
    @Column(nullable = false)
    private Long otherId;
    @JsonProperty
    @Column(nullable = false)
    private String name;
    @JsonProperty
    @Column(nullable = false)
    private Double price;
    @JsonProperty
    private Integer amount;
    @JsonProperty
    private LocalDateTime meetDate;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "treatment_id")
    @JsonIgnore
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private TreatmentEntity treatment;

    public Appointment(AppointmentCalendarDto appointmentCalendarDto) {
        this.otherId = appointmentCalendarDto.getId();
        this.name = appointmentCalendarDto.getTitle();
        this.price = appointmentCalendarDto.getCost();
        this.meetDate = LocalDateTime.of(appointmentCalendarDto.getDate(), appointmentCalendarDto.getFromTime());
        this.amount = 1;
    }

    public Appointment(Long id, String name, Double price, Integer amount, LocalDateTime meetDate, TreatmentEntity treatment) {
        this.id = id;
        this.otherId = otherId;
        this.name = name;
        this.price = price;
        this.amount = amount;
        this.meetDate = meetDate;
        this.treatment = treatment;
    }


}
