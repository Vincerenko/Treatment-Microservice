package com.grid.dynamics.demoprojecthospital.utils;

import com.grid.dynamics.demoprojecthospital.dto.TreatmentSaveDto;
import com.grid.dynamics.demoprojecthospital.exceptions.ApiRequestExceptionTreatment;
import org.springframework.stereotype.Component;

@Component
public class TreatmentValidator {

    public boolean checkTreatmentValueIsNull(TreatmentSaveDto treatmentSaveDto) {
        if (treatmentSaveDto == null) {
            throw new ApiRequestExceptionTreatment("Treatment can't be empty or null.");
        } else if (treatmentSaveDto.getStatus() == null) {
            throw new ApiRequestExceptionTreatment("Field 'status' can't be null.");
        } else if (treatmentSaveDto.getTitle() == null) {
            throw new ApiRequestExceptionTreatment("Field 'Title' can't be null.");
        } else if (treatmentSaveDto.getTitle().isEmpty()) {
            throw new ApiRequestExceptionTreatment("Field 'Title' can't be empty.");
        } else if (treatmentSaveDto.getDoctorId() == null) {
            throw new ApiRequestExceptionTreatment("Field 'Doctor id' can't be null or empty.");
        } else if (treatmentSaveDto.getRoom() == null) {
            throw new ApiRequestExceptionTreatment("Field 'Room' can't be null.");
        } else if (treatmentSaveDto.getRoom().isEmpty()) {
            throw new ApiRequestExceptionTreatment("Field 'Room' can't be empty.");
        } else if (treatmentSaveDto.getPrice() == null) {
            throw new ApiRequestExceptionTreatment("Field 'Price' can't be null or empty.");
        } else if (treatmentSaveDto.getDuringDay() == null) {
            throw new ApiRequestExceptionTreatment("Field 'During Day' can't be null.");
        } else if (treatmentSaveDto.getDuringDay() == 0) {
            throw new ApiRequestExceptionTreatment("Field 'During Day' can't be zero '0'.");
        } else if (treatmentSaveDto.getAppointmentId() == null) {
            throw new ApiRequestExceptionTreatment("Field 'Appointment id' can't be null or empty.");
        } else if (treatmentSaveDto.getDescription() == null) {
            throw new ApiRequestExceptionTreatment("Field 'Description' can't be null.");
        } else if (treatmentSaveDto.getDescription().isEmpty()) {
            throw new ApiRequestExceptionTreatment("Field 'Description' can't be empty.");
        } else if (treatmentSaveDto.getPatientId() == null) {
            throw new ApiRequestExceptionTreatment("Field 'PatientId' can't be null.");
        } else if (treatmentSaveDto.getCurrency() == null) {
            throw new ApiRequestExceptionTreatment("Field 'Currency' can't be null.");
        } else if (treatmentSaveDto.getCurrency().isEmpty()) {
            throw new ApiRequestExceptionTreatment("Field 'Currency' can't be empty.");
        }
        return true;
    }
}
