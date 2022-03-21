package com.grid.dynamics.demoprojecthospital.services;

import com.grid.dynamics.demoprojecthospital.dto.TreatmentDto;
import com.grid.dynamics.demoprojecthospital.dto.TreatmentSaveDto;
import com.grid.dynamics.demoprojecthospital.exceptions.ApiRequestExceptionTreatment;
import com.grid.dynamics.demoprojecthospital.models.Appointment;
import com.grid.dynamics.demoprojecthospital.models.TreatmentEntity;
import com.grid.dynamics.demoprojecthospital.models.enums.CurrencyEnum;
import com.grid.dynamics.demoprojecthospital.models.enums.Status;
import com.grid.dynamics.demoprojecthospital.models.wrapper.AppointmentCalendarDto;
import com.grid.dynamics.demoprojecthospital.repository.TreatmentRepository;
import com.grid.dynamics.demoprojecthospital.services.api.CalendarServiceApi;
import com.grid.dynamics.demoprojecthospital.services.api.CurrencyServiceApi;
import com.grid.dynamics.demoprojecthospital.utils.Rounder;
import com.grid.dynamics.demoprojecthospital.utils.TreatmentValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * This class describe business logic between TreatmentController and TreatmentRepository and operations CRUD.
 */
@Service
@RequiredArgsConstructor
public class TreatmentService {

    /**
     * CurrencyServiceApi provide course exchange by today.
     */
    private final CurrencyServiceApi currencyServiceApi;
    /**
     * TreatmentRepository provide CRUD operations and other custom requests.
     */
    private final TreatmentRepository treatmentRepository;
    /**
     * TreatmentValidator is validating received Object and his fields that will be saved by  TreatmentRepository
     */
    private final TreatmentValidator treatmentValidator;
    private final AppointmentService appointmentService;
    private final CalendarServiceApi calendarServiceApi;

    /**
     * @return List of TreatmentDto from dataBase Treatments (all history from hospital)
     */
    public List<TreatmentDto> getAllTreatments() {
        List<TreatmentEntity> all = treatmentRepository.findAll();
        List<TreatmentDto> result = new ArrayList<>();
        for (TreatmentEntity treatmentElement : all) {
            result.add(new TreatmentDto(treatmentElement));
        }
        return result;
    }

    /**
     * Method provides all history of treatments by PatientId with needed currency ("UAH","RUB","EUR","USD")
     *
     * @param id       demand PatientId
     * @param currency demand type of currency ("UAH","RUB","EUR","USD")
     * @return all treatments which has patient with PatientId
     */
    public List<TreatmentDto> getAllTreatmentsByPatientId(Long id, String currency) {
        List<TreatmentDto> collect = treatmentRepository.findAllByPatientId(id).stream().map(TreatmentDto::new).collect(Collectors.toList());
        List<TreatmentDto> result = new ArrayList<>();
        for (TreatmentDto treatmentElement : collect) {
            if (currency.equals(CurrencyEnum.USD.name())) {
                treatmentElement.setPrice(Rounder.roundAvoid(treatmentElement.getPrice() / currencyServiceApi.getUSD()[0].getRate(), 2));
                treatmentElement.setCurrency(currency);
            } else if (currency.equals(CurrencyEnum.RUB.name())) {
                treatmentElement.setPrice(Rounder.roundAvoid(treatmentElement.getPrice() / currencyServiceApi.getRUB()[0].getRate(), 2));
                treatmentElement.setCurrency(currency);
            } else if (currency.equals(CurrencyEnum.EUR.name())) {
                treatmentElement.setPrice(Rounder.roundAvoid(treatmentElement.getPrice() / currencyServiceApi.getEUR()[0].getRate(), 2));
                treatmentElement.setCurrency(currency);
            }
            result.add(treatmentElement);
        }
        return result;
    }

    public List<TreatmentDto> getAllTreatmentsByPatientId(Long id, String currency, int numberOfPage, int countItems) {
        Pageable pageable = PageRequest.of(numberOfPage, countItems);
        List<TreatmentDto> collect = treatmentRepository.findAllByPatientId(id, pageable).stream().map(TreatmentDto::new).collect(Collectors.toList());
        List<TreatmentDto> result = new ArrayList<>();
        for (TreatmentDto treatmentElement : collect) {
            if (currency.equals(CurrencyEnum.USD.name())) {
                treatmentElement.setPrice(Rounder.roundAvoid(treatmentElement.getPrice() / currencyServiceApi.getUSD()[0].getRate(), 2));
                treatmentElement.setCurrency(currency);
            } else if (currency.equals(CurrencyEnum.RUB.name())) {
                treatmentElement.setPrice(Rounder.roundAvoid(treatmentElement.getPrice() / currencyServiceApi.getRUB()[0].getRate(), 2));
                treatmentElement.setCurrency(currency);
            } else if (currency.equals(CurrencyEnum.EUR.name())) {
                treatmentElement.setPrice(Rounder.roundAvoid(treatmentElement.getPrice() / currencyServiceApi.getEUR()[0].getRate(), 2));
                treatmentElement.setCurrency(currency);
            }
            result.add(treatmentElement);
        }
        return result;
    }


    /**
     * @param id demand PatientId
     * @return all Treatments that has Patient but in default currency ("UAH")
     */
    public List<TreatmentDto> getAllTreatmentsByPatientId(Long id, int numberOfPage, int countItems) {
        Pageable pageable = PageRequest.of(numberOfPage, countItems);
        List<TreatmentEntity> allByPatientId = treatmentRepository.findAllByPatientId(id, pageable);
        List<TreatmentDto> result = new ArrayList<>();
        for (TreatmentEntity treatmentElement : allByPatientId) {
            result.add(new TreatmentDto(treatmentElement));
        }
        return result;
    }

    public List<TreatmentDto> getAllTreatmentsByPatientId(Long id) {
        List<TreatmentEntity> allByPatientId = treatmentRepository.findAllByPatientId(id);
        List<TreatmentDto> result = new ArrayList<>();
        for (TreatmentEntity treatmentElement : allByPatientId) {
            result.add(new TreatmentDto(treatmentElement));
        }
        return result;
    }

    public List<TreatmentDto> getAllTreatmentsByPatientIdAndDoctorId(Long patientId, Long doctorId) {
        long idOfTreat = 0;
        Set<AppointmentCalendarDto> setForSave = new LinkedHashSet<>();
        AppointmentCalendarDto[] appointmentFromServer = calendarServiceApi.getAppointmentFromServer(patientId, doctorId).getBody();
        List<TreatmentEntity> treatmentEntities = treatmentRepository.findTreatmentEntityByDoctorIdAndPatientId(doctorId, patientId);
        for (TreatmentEntity treatmentEntity : treatmentEntities) {
            for (int j = 0; j < appointmentFromServer.length; j++) {
                if (treatmentEntity.getStartDate().isBefore(appointmentFromServer[j].getDate()) && treatmentEntity.getEndDate().isAfter(appointmentFromServer[j].getDate())) {
                    int finalJ = j;
                    if (treatmentEntity.getAppointment().stream().noneMatch(n -> n.getOtherId() == appointmentFromServer[finalJ].getId())) {
                        setForSave.add(appointmentFromServer[j]);
                        idOfTreat = treatmentEntity.getId();
                    }
                }
            }
        }
        for (AppointmentCalendarDto dto : setForSave) {
            appointmentService.saveAppointment(new Appointment(dto), idOfTreat);
        }
        List<TreatmentDto> result = new ArrayList<>();
        for (TreatmentEntity treatmentElement : treatmentEntities) {
            result.add(new TreatmentDto(treatmentElement));
        }
        return result;
    }

    /**
     * @param treatmentSaveDto receive this model as param without dates.
     * @return if receiving Entity passed all checks by TreatmentValidator and go ahead in loop "if", return true.
     * If any field doesn't pass check by TreatmentValidator, return false.
     */
    public boolean saveTreatment(TreatmentSaveDto treatmentSaveDto) {
        if (treatmentValidator.checkTreatmentValueIsNull(treatmentSaveDto)) {
            TreatmentEntity treatmentEntity = new TreatmentEntity(treatmentSaveDto);
            treatmentEntity.getAppointment().forEach(n -> n.setTreatment(treatmentEntity));
            treatmentEntity.getMedicine().forEach(n -> n.setTreatment(treatmentEntity));
            treatmentSaveDto.getAppointment().forEach(n -> treatmentEntity.setPrice(treatmentEntity.getPrice() + (n.getPrice() * n.getCount())));
            treatmentSaveDto.getMedicine().forEach(n -> treatmentEntity.setPrice(treatmentEntity.getPrice() + (n.getPrice() * n.getCount())));
            treatmentEntity.setCurrency("UAH");
            treatmentRepository.save(treatmentEntity);
            return true;
        }
        return false;
    }

    /**
     * @param id receive TreatmentId and delete by this Id.
     */
    public void deleteById(Long id) {
        treatmentRepository.deleteById(id);
    }

    /**
     * This method allows change type of treatment by Treatment Id.
     *
     * @param status receive type of status just like that: PREPARING, IN_PROGRESS, FINISHED.
     * @param id     receive TreatmentId
     */
    public void updateTreatment(String status, Long id) {
        treatmentRepository.findById(id)
                .orElseThrow(() -> new ApiRequestExceptionTreatment("Don't have treatment with: " + id + " id."));
        switch (status) {
            case "FINISHED":
                treatmentRepository.updateTreatmentStatusById(Status.FINISHED, id);
                break;
            case "IN_PROGRESS":
                treatmentRepository.updateTreatmentStatusById(Status.IN_PROGRESS, id);
                break;
            case "PREPARING":
                treatmentRepository.updateTreatmentStatusById(Status.PREPARING, id);
                break;
            default:
                throw new ApiRequestExceptionTreatment("You request in regard status must be just like that: PREPARING,IN_PROGRESS,FINISHED");
        }
    }

    public List<TreatmentEntity> getAllTreatmentsByPatientIdAndRangeDates(LocalDate beforeDate, LocalDate afterDate, Long patientId, int numberOfPage, int countItems) {
        Pageable pageable = PageRequest.of(numberOfPage, countItems);
        return treatmentRepository.findAllByStartDateGreaterThanEqualAndEndDateLessThanEqualAndPatientIdIs(beforeDate, afterDate, patientId, pageable);
    }

    public List<TreatmentEntity> getAllTreatmentsByPatientIdAndRangeDates(LocalDate beforeDate, LocalDate afterDate, Long patientId) {
        return treatmentRepository.findAllByStartDateGreaterThanEqualAndEndDateLessThanEqualAndPatientIdIs(beforeDate, afterDate, patientId);
    }

}
