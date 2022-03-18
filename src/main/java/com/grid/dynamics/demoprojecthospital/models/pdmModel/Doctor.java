package com.grid.dynamics.demoprojecthospital.models.pdmModel;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
public class Doctor {
    private String name;
    private LocalDate birthday;
    private String phone;
    private String gender;
    private String department;
    private String address;
    private String biography;
    private List<String> skills;
    private List<Long> patientIds;
    private List<Long> listRoomIds;
}