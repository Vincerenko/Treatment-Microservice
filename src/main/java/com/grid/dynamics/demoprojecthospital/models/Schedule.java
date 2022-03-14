package com.grid.dynamics.demoprojecthospital.models;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "schedules")
public class Schedule implements Serializable {
    @Id
    @SequenceGenerator(
            name = "schedule_sequence",
            sequenceName = "schedule_sequence",
            allocationSize = 1
    )
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "schedule_sequence"
    )
    @Column(name = "schedule_id")
    @JsonProperty
    private Long id;
    @JsonProperty
    private Long patientId;
    @JsonProperty
    private Long doctorId;
    @OneToMany(mappedBy = "schedule", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonProperty(namespace = "reception_medicines")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private List<ReceptionMedicine> receptionMedicines = new ArrayList<>();

}
