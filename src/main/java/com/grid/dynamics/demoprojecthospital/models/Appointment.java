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
//@AllArgsConstructor
@NoArgsConstructor
@Data
//@RequiredArgsConstructor
public class Appointment implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonProperty
    private Long id;
    private Long otherId;
    @JsonProperty
    @Column(nullable = false)
    private String name;
    @JsonProperty
    @Column(nullable = false)
    private Double price;
    @JsonProperty
    private Integer count;
    @JsonProperty
    private LocalDateTime meetDate;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "treatment_id")
    @JsonIgnore
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private TreatmentEntity treatment;

    public Appointment(AppointmentCalendarDto appointmentCalendarDto){
        this.otherId= appointmentCalendarDto.getId();
        this.name= appointmentCalendarDto.getTitle();
        this.price= appointmentCalendarDto.getCost();
        this.meetDate=LocalDateTime.of(appointmentCalendarDto.getDate(),appointmentCalendarDto.getFromTime());
        this.count=1;
    }

    public Appointment(Long id, String name, Double price, Integer count, LocalDateTime meetDate, TreatmentEntity treatment) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.count = count;
        this.meetDate = meetDate;
        this.treatment = treatment;
    }
}
