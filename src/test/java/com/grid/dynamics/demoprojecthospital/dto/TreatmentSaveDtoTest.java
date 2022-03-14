package com.grid.dynamics.demoprojecthospital.dto;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import java.util.Optional;

import static org.junit.Assert.*;

public class TreatmentSaveDtoTest {

    @Test
    public void getId() {
        TreatmentSaveDto treatmentSaveDto = new TreatmentSaveDto();
        treatmentSaveDto.setId(456L);
        Long id = treatmentSaveDto.getId();
        Assertions.assertEquals(456,id);
    }

    @Test
    public void getStatus() {
    }

    @Test
    public void getTitle() {
    }

    @Test
    public void getDoctorId() {
    }

    @Test
    public void getPatientId() {
    }

    @Test
    public void getRoom() {
    }

    @Test
    public void getCurrency() {
    }

    @Test
    public void getPrice() {
    }

    @Test
    public void getDuringDay() {
    }

    @Test
    public void getAppointmentId() {
    }

    @Test
    public void getDescription() {
    }

    @Test
    public void getMedicine() {
    }

    @Test
    public void getAppointment() {
    }

    @Test
    public void setId() {
    }

    @Test
    public void setStatus() {
    }

    @Test
    public void setTitle() {
    }

    @Test
    public void setDoctorId() {
    }

    @Test
    public void setPatientId() {
    }

    @Test
    public void setRoom() {
    }

    @Test
    public void setCurrency() {
    }

    @Test
    public void setPrice() {
    }

    @Test
    public void setDuringDay() {
    }

    @Test
    public void setAppointmentId() {
    }

    @Test
    public void setDescription() {
    }

    @Test
    public void setMedicine() {
    }

    @Test
    public void setAppointment() {
    }

    @Test
    public void canEqual() {
    }
}