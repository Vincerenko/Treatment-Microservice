package com.grid.dynamics.demoprojecthospital.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.grid.dynamics.demoprojecthospital.models.enums.FrequencyEnum;
import com.grid.dynamics.demoprojecthospital.models.enums.TimesADayEnum;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "receptionMedicines")
public class ReceptionMedicine implements Serializable {
    @Id
    @SequenceGenerator(
            name = "treatment_sequence",
            sequenceName = "treatment_sequence",
            allocationSize = 1
    )
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "treatment_sequence"
    )
    @Column(name = "take_medicine_id")
    @JsonProperty
    private Long id;
    @JsonProperty
    private String nameMedicine;
    @JsonProperty
    @Enumerated(EnumType.STRING)
    private FrequencyEnum frequencyEnums;
    @JsonProperty
    @Enumerated(EnumType.STRING)
    private TimesADayEnum timesADayEnums;
    @JsonProperty
    private String description;
    @JsonProperty
    @ElementCollection
    private List<LocalDateTime> localDateTimes = new ArrayList<>();
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "schedule_id")
    @JsonIgnore
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Schedule schedule;

}
