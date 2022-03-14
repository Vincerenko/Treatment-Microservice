package com.grid.dynamics.demoprojecthospital.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
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


}