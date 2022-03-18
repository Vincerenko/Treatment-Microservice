package com.grid.dynamics.demoprojecthospital.models.pdmModel;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
public class Patient {
    private LocalDate birthday;
    private String name;
    private String phone;
    private String relation;
    private String occupation;
    private String address;
    private String description;
}