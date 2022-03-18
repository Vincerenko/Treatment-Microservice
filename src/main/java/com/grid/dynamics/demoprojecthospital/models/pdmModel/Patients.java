package com.grid.dynamics.demoprojecthospital.models.pdmModel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Patients {
    private List<Patient> patients;
}