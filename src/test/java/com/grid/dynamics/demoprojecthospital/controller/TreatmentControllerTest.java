package com.grid.dynamics.demoprojecthospital.controller;

import com.grid.dynamics.demoprojecthospital.dto.TreatmentDto;
import com.grid.dynamics.demoprojecthospital.dto.TreatmentSaveDto;
import com.grid.dynamics.demoprojecthospital.exceptions.ApiRequestExceptionTreatment;
import com.grid.dynamics.demoprojecthospital.models.TreatmentEntity;
import com.grid.dynamics.demoprojecthospital.models.enums.Status;
import com.grid.dynamics.demoprojecthospital.services.TreatmentService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.client.ResourceAccessException;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class TreatmentControllerTest {
    @Mock
    private TreatmentService treatmentService;
    @InjectMocks
    private TreatmentController treatmentController;

    @Test
    void ShouldGetTreatment() {
        TreatmentDto treatmentDto = new TreatmentDto();
        List<TreatmentDto> expected = List.of(treatmentDto);
        Mockito.when(treatmentService.getAllTreatmentsByPatientId(1L)).thenReturn(expected);
        List<TreatmentDto> actual = treatmentController.getTreatment(1L);
        assertDoesNotThrow(() -> SQLException.class);
        assertEquals(expected, actual);
    }

    @Test
    void ShouldThrowExceptionWhenGetTreatment() {
        Mockito.when(treatmentService.getAllTreatmentsByPatientId(1L)).thenThrow(ApiRequestExceptionTreatment.class);
        assertThrows(ApiRequestExceptionTreatment.class, () -> treatmentService.getAllTreatmentsByPatientId(1L));
    }

    @Test
    void getTreatmentWithCurrencyByPatientId() {
        TreatmentDto treatmentDto = new TreatmentDto();
        List<TreatmentDto> expected = List.of(treatmentDto);
        Mockito.when(treatmentService.getAllTreatmentsByPatientId(1L, "USD")).thenReturn(expected);
        List<TreatmentDto> actual = treatmentController.getTreatmentWithCurrencyByPatientId(1L, "USD");
        assertEquals(expected, actual);
    }

    @Test
    void ShouldThrowExceptionWhenGetTreatmentWithCurrencyByPatientId() {
        List<TreatmentDto> treatmentDtos = new ArrayList<>();
        Mockito.when(treatmentService.getAllTreatmentsByPatientId(1L, "USD")).thenReturn(treatmentDtos);
        assertThrows(ApiRequestExceptionTreatment.class, () -> treatmentController.getTreatmentWithCurrencyByPatientId(1L, "USD"));
    }

    @Test
    void ShouldThrowExceptionWhenGetTreatmentWithCurrencyByPatientIdAndDoesntHaveAccessToApiBank() {
        Mockito.when(treatmentService.getAllTreatmentsByPatientId(1L, "USD")).thenThrow(ResourceAccessException.class);
        assertThrows(ApiRequestExceptionTreatment.class, () -> treatmentController.getTreatmentWithCurrencyByPatientId(1L, "USD"));
    }

    @Test
    void ShouldGetAll() {
        TreatmentDto treatmentDto = new TreatmentDto();
        List<TreatmentDto> expected = List.of(treatmentDto);
        Mockito.when(treatmentService.getAllTreatments()).thenReturn(expected);
        List<TreatmentDto> actual = treatmentController.getAll();
        assertEquals(expected, actual);
    }

    @Test
    void ShouldThrowExceptionWhenGetAll() {
        List<TreatmentDto> expected = new ArrayList<>();
        Mockito.when(treatmentService.getAllTreatments()).thenReturn(expected);
        assertThrows(ApiRequestExceptionTreatment.class, () -> treatmentController.getAll());
    }

    @Test
    void ShouldSaveNewTreatment() {
        TreatmentSaveDto treatmentSaveDto = new TreatmentSaveDto();
        Mockito.when(treatmentService.saveTreatment(treatmentSaveDto)).thenReturn(true);
        treatmentController.saveNewTreatment(treatmentSaveDto);
    }

    @Test
    void ShouldThrowExceptionWhenSaveNewTreatment() {
        Mockito.when(treatmentService.saveTreatment(null)).thenReturn(false);
        assertThrows(ApiRequestExceptionTreatment.class, () -> treatmentController.saveNewTreatment(null));
    }

    @Test
    void ShouldDeleteTreatmentById() {
        Mockito.doNothing().when(treatmentService).deleteById(1L);
        treatmentController.deleteTreatmentById(1L);
    }

    @Test
    void ShouldUpdateTreatment() {
        Mockito.doNothing().when(treatmentService).updateTreatment("FINISHED", 1L);
        treatmentController.updateTreatment(1L, "FINISHED");
    }

    @Test
    void ShouldGetTreatmentByRangeOfDate() {
        Long duringDay = 7L;
        LocalDate firstPeriod = LocalDate.of(2022, Month.FEBRUARY, 2);
        LocalDate secondPeriod = firstPeriod.plusDays(duringDay);
        TreatmentEntity treatmentEntity = new TreatmentEntity(1L, Status.IN_PROGRESS, "title", 2L, 3L, "K-103", 300.0,
                "USD", firstPeriod, secondPeriod, duringDay, 5L, "Description", new HashSet<>(), new HashSet<>());
        List<TreatmentEntity> expected = List.of(treatmentEntity);
        Mockito.when(treatmentService.getAllTreatmentsByPatientIdAndRangeDates(firstPeriod.minusDays(2), secondPeriod.plusDays(2), 3L)).thenReturn(expected);
        List<TreatmentEntity> actual = treatmentController.getTreatmentByRangeOfDate(3L, firstPeriod.minusDays(2).toString(), secondPeriod.plusDays(2).toString());
        assertEquals(expected, actual);
    }

    @Test
    void ShouldThrowExceptionGetTreatmentByRangeOfDate() {
        Long duringDay = 7L;
        LocalDate firstPeriod = LocalDate.of(2022, Month.FEBRUARY, 2);
        LocalDate secondPeriod = firstPeriod.plusDays(duringDay);
        List<TreatmentEntity> treatmentEntities = new ArrayList<>();
        Mockito.when(treatmentService.getAllTreatmentsByPatientIdAndRangeDates(firstPeriod, secondPeriod, 3L)).thenReturn(treatmentEntities);
        assertThrows(ApiRequestExceptionTreatment.class, () -> treatmentController.getTreatmentByRangeOfDate(3L, firstPeriod.toString(), secondPeriod.toString()));
    }

    @Test
    void shouldTestGetTreatmentAndThrowException(){
        List<TreatmentDto> treatmentDtos = new ArrayList<>();
        Mockito.when(treatmentService.getAllTreatmentsByPatientId(999L)).thenReturn(treatmentDtos);
        assertThrows(ApiRequestExceptionTreatment.class, () -> treatmentController.getTreatment(999L));
    }
}