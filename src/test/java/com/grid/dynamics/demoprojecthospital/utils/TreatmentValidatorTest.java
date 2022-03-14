package com.grid.dynamics.demoprojecthospital.utils;

import com.grid.dynamics.demoprojecthospital.dto.TreatmentSaveDto;
import com.grid.dynamics.demoprojecthospital.exceptions.ApiRequestExceptionTreatment;
import com.grid.dynamics.demoprojecthospital.models.enums.Status;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
class TreatmentValidatorTest {
    @Mock
    private RuntimeException runtimeException;
    @Mock
    private ApiRequestExceptionTreatment apiRequestExceptionTreatment;
    @InjectMocks
    private TreatmentValidator treatmentValidator;


    @Test
    void ShouldCheckTreatmentIsNull() {
        assertThrows(ApiRequestExceptionTreatment.class, () -> treatmentValidator.checkTreatmentValueIsNull(null));
    }

    @Test
    void ShouldCheckTreatmentStatusIsNull() {
        TreatmentSaveDto treatmentSaveDto = new TreatmentSaveDto(456L, null, "Treat stomach", 569L, 345L, "K-154", "UAH", 6300.0,
                7L, 654L, "Patient has complain to sick stomach", new HashSet<>(), new HashSet<>());
        assertThrows(ApiRequestExceptionTreatment.class, () -> treatmentValidator.checkTreatmentValueIsNull(treatmentSaveDto));
    }

    @Test
    void ShouldCheckTitleIsNull() {
        TreatmentSaveDto treatmentSaveDto = new TreatmentSaveDto(456L, Status.IN_PROGRESS, null, 569L, 345L, "K-154", "UAH", 6300.0,
                7L, 654L, "Patient has complain to sick stomach", new HashSet<>(), new HashSet<>());
        assertThrows(ApiRequestExceptionTreatment.class, () -> treatmentValidator.checkTreatmentValueIsNull(treatmentSaveDto));
    }

    @Test
    void ShouldCheckTitleIsEmpty() {
        TreatmentSaveDto treatmentSaveDto = new TreatmentSaveDto(456L, Status.IN_PROGRESS, "", 569L, 345L, "K-154", "UAH", 6300.0,
                7L, 654L, "Patient has complain to sick stomach", new HashSet<>(), new HashSet<>());
        assertThrows(ApiRequestExceptionTreatment.class, () -> treatmentValidator.checkTreatmentValueIsNull(treatmentSaveDto));
    }

    @Test
    void ShouldCheckDoctorIdIsNull() {
        TreatmentSaveDto treatmentSaveDto = new TreatmentSaveDto(456L, Status.IN_PROGRESS, "Treat stomach", null, 345L, "K-154", "UAH", 6300.0,
                7L, 654L, "Patient has complain to sick stomach", new HashSet<>(), new HashSet<>());
        assertThrows(ApiRequestExceptionTreatment.class, () -> treatmentValidator.checkTreatmentValueIsNull(treatmentSaveDto));
    }

    @Test
    void ShouldCheckPatientIdIsNull() {
        TreatmentSaveDto treatmentSaveDto = new TreatmentSaveDto(456L, Status.IN_PROGRESS, "Treat stomach", 345L, null, "K-154", "UAH", 6300.0,
                7L, 654L, "Patient has complain to sick stomach", new HashSet<>(), new HashSet<>());
        assertThrows(ApiRequestExceptionTreatment.class, () -> treatmentValidator.checkTreatmentValueIsNull(treatmentSaveDto));
    }

    @Test
    void ShouldCheckRoomIsNull() {
        TreatmentSaveDto treatmentSaveDto = new TreatmentSaveDto(456L, Status.IN_PROGRESS, "Treat stomach", 569L, 345L, null, "UAH", 6300.0,
                7L, 654L, "Patient has complain to sick stomach", new HashSet<>(), new HashSet<>());
        assertThrows(ApiRequestExceptionTreatment.class, () -> treatmentValidator.checkTreatmentValueIsNull(treatmentSaveDto));
    }

    @Test
    void ShouldCheckRoomIsEmpty() {
        TreatmentSaveDto treatmentSaveDto = new TreatmentSaveDto(456L, Status.IN_PROGRESS, "Treat stomach", 569L, 345L, "", "UAH", 6300.0,
                7L, 654L, "Patient has complain to sick stomach", new HashSet<>(), new HashSet<>());
        assertThrows(ApiRequestExceptionTreatment.class, () -> treatmentValidator.checkTreatmentValueIsNull(treatmentSaveDto));
    }

    @Test
    void ShouldCheckCurrencyIsEmpty() {
        TreatmentSaveDto treatmentSaveDto = new TreatmentSaveDto(456L, Status.IN_PROGRESS, "Treat stomach", 569L, 345L, "154", "", 6300.0,
                7L, 654L, "Patient has complain to sick stomach", new HashSet<>(), new HashSet<>());
        assertThrows(ApiRequestExceptionTreatment.class, () -> treatmentValidator.checkTreatmentValueIsNull(treatmentSaveDto));

    }

    @Test
    void ShouldCheckCurrencyIsNull() {
        TreatmentSaveDto treatmentSaveDto = new TreatmentSaveDto(456L, Status.IN_PROGRESS, "Treat stomach", 569L, 345L, "154", null, 6300.0,
                7L, 654L, "Patient has complain to sick stomach", new HashSet<>(), new HashSet<>());
        assertThrows(ApiRequestExceptionTreatment.class, () -> treatmentValidator.checkTreatmentValueIsNull(treatmentSaveDto));
    }

    @Test
    void ShouldCheckPriceIsNull() {
        TreatmentSaveDto treatmentSaveDto = new TreatmentSaveDto(456L, Status.IN_PROGRESS, "Treat stomach", 569L, 345L, "154", "RUB", null,
                7L, 654L, "Patient has complain to sick stomach", new HashSet<>(), new HashSet<>());
        assertThrows(ApiRequestExceptionTreatment.class, () -> treatmentValidator.checkTreatmentValueIsNull(treatmentSaveDto));
    }

    @Test
    void ShouldCheckDuringDayIsNull() {
        TreatmentSaveDto treatmentSaveDto = new TreatmentSaveDto(456L, Status.IN_PROGRESS, "Treat stomach", 569L, 345L, "154", null, 6300.0,
                null, 654L, "Patient has complain to sick stomach", new HashSet<>(), new HashSet<>());
        assertThrows(ApiRequestExceptionTreatment.class, () -> treatmentValidator.checkTreatmentValueIsNull(treatmentSaveDto));
    }

    @Test
    void ShouldCheckDuringDayIsZero() {
        TreatmentSaveDto treatmentSaveDto = new TreatmentSaveDto(456L, Status.IN_PROGRESS, "Treat stomach", 569L, 345L, "154", null, 6300.0,
                0L, 654L, "Patient has complain to sick stomach", new HashSet<>(), new HashSet<>());
        assertThrows(ApiRequestExceptionTreatment.class, () -> treatmentValidator.checkTreatmentValueIsNull(treatmentSaveDto));
    }

    @Test
    void ShouldCheckAppointmentIdIsNull() {
        TreatmentSaveDto treatmentSaveDto = new TreatmentSaveDto(456L, Status.IN_PROGRESS, "Treat stomach", 569L, 345L, "154", "RUB", 6300.0,
                7L, null, "Patient has complain to sick stomach", new HashSet<>(), new HashSet<>());
        assertThrows(ApiRequestExceptionTreatment.class, () -> treatmentValidator.checkTreatmentValueIsNull(treatmentSaveDto));
    }

    @Test
    void ShouldCheckDescriptionIsNull() {
        TreatmentSaveDto treatmentSaveDto = new TreatmentSaveDto(456L, Status.IN_PROGRESS, "Treat stomach", 569L, 345L, "154", "RUB", 6300.0,
                7L, 654L, null, new HashSet<>(), new HashSet<>());
        assertThrows(ApiRequestExceptionTreatment.class, () -> treatmentValidator.checkTreatmentValueIsNull(treatmentSaveDto));
    }

    @Test
    void ShouldCheckDescriptionIsEmpty() {
        TreatmentSaveDto treatmentSaveDto = new TreatmentSaveDto(456L, Status.IN_PROGRESS, "Treat stomach", 569L, 345L, "154", "RUB", 6300.0,
                7L, 654L, "", new HashSet<>(), new HashSet<>());
        assertThrows(ApiRequestExceptionTreatment.class, () -> treatmentValidator.checkTreatmentValueIsNull(treatmentSaveDto));
    }

    @Test
    void ShouldCheckIsEntityIsOkey() {
        TreatmentSaveDto treatmentSaveDto = new TreatmentSaveDto(456L, Status.IN_PROGRESS, "Treat stomach", 569L, 345L, "154", "RUB", 6300.0,
                7L, 654L, "Everything is okey", new HashSet<>(), new HashSet<>());
        boolean result = treatmentValidator.checkTreatmentValueIsNull(treatmentSaveDto);
        boolean expected = true;
        assertEquals(expected, result);
    }

}