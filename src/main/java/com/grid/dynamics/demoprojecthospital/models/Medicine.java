package com.grid.dynamics.demoprojecthospital.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.grid.dynamics.demoprojecthospital.models.wrapper.MedicineDto;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

/**
 * This class describe model of appointment in hospital that mapped to Data Base and this class is used in business logic
 */
@Entity(name = "medicine")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Medicine implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonProperty
    private Long id;
    @JsonProperty
    @Column(name = "name", nullable = false)
    private String name;
    @JsonProperty
    @Column(name = "price", nullable = false)
    private Double price;
    @JsonProperty
    @Column(name = "count", nullable = false)
    private Integer count;
    @JsonProperty
    @Column(name = "otherId")
    private Long otherId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "treatment_id")
    @JsonIgnore
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private TreatmentEntity treatment;

    public Medicine(MedicineDto medicineDto) {
        this.otherId = medicineDto.getId();
        this.name = medicineDto.getName();
        this.price = medicineDto.getPrice();
        this.count = 1;
    }

}
