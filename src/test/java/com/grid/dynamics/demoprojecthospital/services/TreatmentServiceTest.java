package com.grid.dynamics.demoprojecthospital.services;

import com.grid.dynamics.demoprojecthospital.dto.TreatmentDto;
import com.grid.dynamics.demoprojecthospital.dto.TreatmentSaveDto;
import com.grid.dynamics.demoprojecthospital.exceptions.ApiRequestExceptionTreatment;
import com.grid.dynamics.demoprojecthospital.models.Currency;
import com.grid.dynamics.demoprojecthospital.models.TreatmentEntity;
import com.grid.dynamics.demoprojecthospital.models.enums.Status;
import com.grid.dynamics.demoprojecthospital.repository.TreatmentRepository;
import com.grid.dynamics.demoprojecthospital.services.api.CurrencyServiceApi;
import com.grid.dynamics.demoprojecthospital.utils.TreatmentValidator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.time.Month;
import java.time.ZoneId;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TreatmentServiceTest {
    @Mock
    private TreatmentValidator treatmentValidator;
    @Mock
    private TreatmentRepository treatmentRepository;
    @Mock
    private RestTemplate restTemplate;
    @Mock
    private CurrencyServiceApi currencyServiceApi;
    @InjectMocks
    private TreatmentService treatmentService;


    @Test
    void ShouldSaveTreatmentWithNullIsFalse() {
        when(treatmentService.saveTreatment(null)).thenReturn(false);
        verify(treatmentRepository, Mockito.times(0)).save(any());
        Assertions.assertFalse(treatmentService.saveTreatment(null));

    }

    @Test
    void ShouldSaveTreatmentIsTrue() {
        TreatmentSaveDto treatmentSaveDto = new TreatmentSaveDto(456L, Status.PREPARING, "Treat stomach", 569L, 345L, "K-154", "UAH", 6300.0,
                7L, 654L, "Patient has complain to sick stomach", new HashSet<>(), new HashSet<>());
        when(treatmentService.saveTreatment(treatmentSaveDto)).thenReturn(true);
        boolean result = treatmentService.saveTreatment(treatmentSaveDto);
        Assertions.assertTrue(result);
    }


    @Test
    void ShouldUpdateStatusWhenIdNotFound() {
        when(treatmentRepository.findById(1L)).thenThrow(ApiRequestExceptionTreatment.class);
        Assertions.assertThrows(ApiRequestExceptionTreatment.class, () -> treatmentService.updateTreatment("FINISHED", 1L));
    }

    @Test
    void ShouldUpdateStatusWhenStatusFinished() {
        when(treatmentRepository.findById(1L)).thenReturn(Optional.of(new TreatmentEntity()));
        doNothing().when(treatmentRepository).updateTreatmentStatusById(Status.FINISHED, 1L);
        treatmentService.updateTreatment("FINISHED", 1L);
        verify(treatmentRepository, times(1)).updateTreatmentStatusById(Status.FINISHED, 1L);
    }

    @Test
    void ShouldUpdateStatusWhenStatusInProgress() {
        when(treatmentRepository.findById(1L)).thenReturn(Optional.of(new TreatmentEntity()));
        doNothing().when(treatmentRepository).updateTreatmentStatusById(Status.IN_PROGRESS, 1L);
        treatmentService.updateTreatment("IN_PROGRESS", 1L);
        verify(treatmentRepository, times(1)).updateTreatmentStatusById(Status.IN_PROGRESS, 1L);
    }

    @Test
    void ShouldUpdateStatusWhenStatusPreparing() {
        when(treatmentRepository.findById(1L)).thenReturn(Optional.of(new TreatmentEntity()));
        doNothing().when(treatmentRepository).updateTreatmentStatusById(Status.PREPARING, 1L);
        treatmentService.updateTreatment("PREPARING", 1L);
        verify(treatmentRepository, times(1)).updateTreatmentStatusById(Status.PREPARING, 1L);
    }

    @Test
    void ShouldUpdateStatusWrongRequest() {
        when(treatmentRepository.findById(1L)).thenReturn(Optional.of(new TreatmentEntity()));
        doNothing().when(treatmentRepository).updateTreatmentStatusById(Status.PREPARING, 1L);
        treatmentService.updateTreatment("PREPARING", 1L);
        Assertions.assertThrows(ApiRequestExceptionTreatment.class, () -> treatmentService.updateTreatment("FINIHED", 1L));
    }

    @Test
    void ShouldAllTreatmentsByPatientId() {
        TreatmentSaveDto treatmentSaveDto = new TreatmentSaveDto(2L, Status.PREPARING, "Treat stomach", 569L, 2L, "K-154", "UAH", 6300.0, 7L,
                7L, "Patient has complain to sick stomach", new HashSet<>(), new HashSet<>());
        List<TreatmentEntity> listEntity = List.of(new TreatmentEntity(treatmentSaveDto));
        when(treatmentRepository.findAllByPatientId(2L)).thenReturn(listEntity);
        List<TreatmentDto> actual = treatmentService.getAllTreatmentsByPatientId(2L);
        List<TreatmentDto> expected = listEntity.stream().map(TreatmentDto::new).collect(Collectors.toList());
        assertEquals(expected, actual);
    }

    @Test
    void ShouldGetAllTreatmentsFromHospital() {
        TreatmentSaveDto treatmentSaveDto = new TreatmentSaveDto(2L, Status.PREPARING, "Treat stomach", 569L, 2L, "K-154", "UAH", 6300.0, 7L,
                7L, "Patient has complain to sick stomach", new HashSet<>(), new HashSet<>());
        List<TreatmentEntity> listEntity = List.of(new TreatmentEntity(treatmentSaveDto));
        when(treatmentRepository.findAll()).thenReturn(listEntity);
        List<TreatmentDto> actual = treatmentService.getAllTreatments();
        List<TreatmentDto> expected = listEntity.stream().map(TreatmentDto::new).collect(Collectors.toList());
        assertEquals(expected, actual);
    }

    @Test
    void ShouldDeleteById() {
        doNothing().when(treatmentRepository).deleteById(1L);
        treatmentService.deleteById(1L);
    }

    @Test
    void ShouldGetAllTreatmentsWithCurrencyUSD() {
        Long duringDay = 7L;
        LocalDate firstPeriod = LocalDate.now(ZoneId.of("UTC"));
        LocalDate secondPeriod = LocalDate.now(ZoneId.of("UTC")).plusDays(duringDay);
        TreatmentDto treatmentDtoUSD = new TreatmentDto(null, Status.IN_PROGRESS, "Treat stomach", 569L, 2L, "154", "USD", 225.0, firstPeriod, duringDay, secondPeriod,
                7L, "Hello", new HashSet<>(), new HashSet<>());
        TreatmentSaveDto treatmentSaveDto = new TreatmentSaveDto(null, Status.IN_PROGRESS, "Treat stomach", 569L, 2L, "154", "UAH", 6300.0,
                7L, 7L, "Hello", new HashSet<>(), new HashSet<>());
        List<TreatmentEntity> treatmentDtos = List.of(new TreatmentEntity(treatmentSaveDto));
        List<TreatmentDto> expected = List.of(treatmentDtoUSD);
        when(treatmentRepository.findAllByPatientId(2L)).thenReturn(treatmentDtos);
        Currency[] currencies = {new Currency(23, "USD", 28.00D, "usd", "20220215")};
        when(currencyServiceApi.getUSD()).thenReturn(currencies);
        List<TreatmentDto> actual = treatmentService.getAllTreatmentsByPatientId(2L, "USD");
        assertEquals(expected, actual);
    }

    @Test
    void ShouldGetAllTreatmentsWithCurrencyEUR() {
        Long duringDay = 7L;
        LocalDate firstPeriod = LocalDate.now(ZoneId.of("UTC"));
        LocalDate secondPeriod = LocalDate.now(ZoneId.of("UTC")).plusDays(duringDay);
        TreatmentDto treatmentDtoUSD = new TreatmentDto(null, Status.IN_PROGRESS, "Treat stomach", 569L, 2L, "154", "EUR", 225.0, firstPeriod, duringDay, secondPeriod,
                7L, "Hello", new HashSet<>(), new HashSet<>());
        TreatmentSaveDto treatmentSaveDto = new TreatmentSaveDto(null, Status.IN_PROGRESS, "Treat stomach", 569L, 2L, "154", "UAH", 6300.0,
                7L, 7L, "Hello", new HashSet<>(), new HashSet<>());
        List<TreatmentEntity> treatmentDtos = List.of(new TreatmentEntity(treatmentSaveDto));
        List<TreatmentDto> expected = List.of(treatmentDtoUSD);
        when(treatmentRepository.findAllByPatientId(2L)).thenReturn(treatmentDtos);
        Currency[] currencies = {new Currency(23, "EUR", 28.00D, "eur", "20220215")};
        when(currencyServiceApi.getEUR()).thenReturn(currencies);
        List<TreatmentDto> actual = treatmentService.getAllTreatmentsByPatientId(2L, "EUR");
        assertEquals(expected, actual);
    }

    @Test
    void ShouldGetAllTreatmentsWithCurrencyRUB() {
        Long duringDay = 7L;
        LocalDate firstPeriod = LocalDate.now(ZoneId.of("UTC"));
        LocalDate secondPeriod = LocalDate.now(ZoneId.of("UTC")).plusDays(duringDay);
        TreatmentDto treatmentDtoUSD = new TreatmentDto(null, Status.IN_PROGRESS, "Treat stomach", 569L, 2L, "154", "RUB", 225.0, firstPeriod, duringDay, secondPeriod,
                7L, "Hello", new HashSet<>(), new HashSet<>());
        TreatmentSaveDto treatmentSaveDto = new TreatmentSaveDto(null, Status.IN_PROGRESS, "Treat stomach", 569L, 2L, "154", "UAH", 6300.0,
                7L, 7L, "Hello", new HashSet<>(), new HashSet<>());
        List<TreatmentEntity> treatmentDtos = List.of(new TreatmentEntity(treatmentSaveDto));
        List<TreatmentDto> expected = List.of(treatmentDtoUSD);
        when(treatmentRepository.findAllByPatientId(2L)).thenReturn(treatmentDtos);
        Currency[] currencies = {new Currency(23, "RUB", 28.00D, "rub", "20220215")};
        when(currencyServiceApi.getRUB()).thenReturn(currencies);
        List<TreatmentDto> actual = treatmentService.getAllTreatmentsByPatientId(2L, "RUB");
        assertEquals(expected, actual);
    }

    @Test
    void ShouldGetAllTreatmentsWithCurrencyUAH() {
        Long duringDay = 7L;
        LocalDate firstPeriod = LocalDate.now(ZoneId.of("UTC"));
        LocalDate secondPeriod = LocalDate.now(ZoneId.of("UTC")).plusDays(duringDay);
        TreatmentDto treatmentDtoUSD = new TreatmentDto(null, Status.IN_PROGRESS, "Treat stomach", 569L, 2L, "154", "UAH", 6300.0, firstPeriod, duringDay, secondPeriod,
                7L, "Hello", new HashSet<>(), new HashSet<>());
        TreatmentSaveDto treatmentSaveDto = new TreatmentSaveDto(null, Status.IN_PROGRESS, "Treat stomach", 569L, 2L, "154", "UAH", 6300.0,
                7L, 7L, "Hello", new HashSet<>(), new HashSet<>());
        List<TreatmentEntity> treatmentDtos = List.of(new TreatmentEntity(treatmentSaveDto));
        List<TreatmentDto> expected = List.of(treatmentDtoUSD);
        when(treatmentRepository.findAllByPatientId(2L)).thenReturn(treatmentDtos);
        List<TreatmentDto> actual = treatmentService.getAllTreatmentsByPatientId(2L);
        assertEquals(expected, actual);
    }

    @Test
    void ShouldGetAllTreatmentsWithRangeByDate() {
        Long duringDay = 10L;
        LocalDate beforeDate = LocalDate.of(2022, Month.FEBRUARY, 14);
        LocalDate afterDate = beforeDate.plusDays(duringDay);
        TreatmentEntity treatmentEntity = new TreatmentEntity(3L, Status.IN_PROGRESS, "Ache tooth", 43L, 20L, "home", 49840.0, "UAH", LocalDate.of(2022, Month.FEBRUARY, 15), LocalDate.of(2022, Month.FEBRUARY, 22), 7L,
                4L, "Hello", new HashSet<>(), new HashSet<>());
        List<TreatmentEntity> expect = List.of(treatmentEntity);
        when(treatmentRepository.findAllByStartDateGreaterThanEqualAndEndDateLessThanEqualAndPatientIdIs(beforeDate, afterDate, 20L))
                .thenReturn(expect);
        List<TreatmentEntity> actual = treatmentService.getAllTreatmentsByPatientIdAndRangeDates(beforeDate, afterDate, 20L);
        assertEquals(expect, actual);
    }

    @Test
    void shouldThrowException() {
        when(treatmentRepository.findById(1L)).thenThrow(ApiRequestExceptionTreatment.class);
        assertThrows(ApiRequestExceptionTreatment.class, () -> treatmentService.updateTreatment("FINISHED",1L));
    }

}
