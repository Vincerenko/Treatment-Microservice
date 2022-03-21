package com.grid.dynamics.demoprojecthospital.models.wrapper;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MedicineDto {
    private Long id;
    private String name;
    private double price;
    private String compound;
    private String contraindications;
    private String termsOfUse;
}