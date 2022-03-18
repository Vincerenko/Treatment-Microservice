package com.grid.dynamics.demoprojecthospital.controller;

import com.grid.dynamics.demoprojecthospital.dto.TreatmentDto;
import com.grid.dynamics.demoprojecthospital.dto.TreatmentSaveDto;
import com.grid.dynamics.demoprojecthospital.exceptions.ApiRequestExceptionTreatment;
import com.grid.dynamics.demoprojecthospital.models.TreatmentEntity;
import com.grid.dynamics.demoprojecthospital.services.TreatmentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.ResourceAccessException;

import java.time.LocalDate;
import java.util.List;

/**
 * The main class for receiving request from Front-End to create, change, delete Treatment.
 */
@RestController
@RequestMapping(path = "api/v1")
@RequiredArgsConstructor
public class TreatmentController {
    private final String responseWithoutID = "Don't have any treatments with this id: %s";
    private final String didntCreate = "Something is going wrong, treatment didn't create.";

    private final TreatmentService treatmentService;


    /**
     * @param id is demanding id of Patient.
     * @return all Treatments of received id of Patient.
     * This method receive id of Patient and return all treatments that patient with this id has.
     */
    @GetMapping("/treatments/")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "treatments sent"),
            @ApiResponse(responseCode = "404", description = "Don't have any treatments")})
    @Operation(summary = "get treatments by patient id", description = "get all treatments from patient history with currency UAH")
    @ResponseStatus(HttpStatus.OK)
    public List<TreatmentDto> getTreatment(@RequestParam Long id,
                                           @RequestParam int numberOfPage,
                                           @RequestParam int countOfItems) {
        if (treatmentService.getAllTreatmentsByPatientId(id, numberOfPage, countOfItems).isEmpty()) {
            throw new ApiRequestExceptionTreatment(String.format(responseWithoutID, id));
        }
        return treatmentService.getAllTreatmentsByPatientId(id, numberOfPage, countOfItems);
    }

    /**
     * @param id       is demanding id of Patient.
     * @param currency receive type of currency, after that will be returned result price in currency that was requested (receive only: "USD", "EUR","RUB","UAH")
     * @return all Treatments of received id of Patient with needed currency.
     */
    @GetMapping("/treatments/{id}/{currency}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "treatments sent"),
            @ApiResponse(responseCode = "404", description = "Don't have any treatments")})
    @Operation(summary = "get treatments by id with currency", description = "get all treatments from patient history with currency USD, RUB or EUR")
    @ResponseStatus(HttpStatus.OK)
    public List<TreatmentDto> getTreatmentWithCurrencyByPatientId(@PathVariable(name = "id") Long id, @PathVariable(name = "currency") String currency) {
        try {
            List<TreatmentDto> allTreatmentsByPatientId = treatmentService.getAllTreatmentsByPatientId(id, currency);
            if (allTreatmentsByPatientId.isEmpty()) {
                throw new ApiRequestExceptionTreatment(String.format(responseWithoutID, id));
            }
            return allTreatmentsByPatientId;
        } catch (ResourceAccessException e) {
            throw new ApiRequestExceptionTreatment("Sorry, but temporarily apps doesn't have access to up-to-date course exchange (USD,EUR,RUB). You should request UAH.");
        }

    }

    @GetMapping("/treatments/{id}/{currency}/{numberOfPage}/{countOfItems}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "treatments sent"),
            @ApiResponse(responseCode = "404", description = "Don't have any treatments")})
    @Operation(summary = "get treatments by id with currency", description = "get all treatments from patient history with currency USD, RUB or EUR")
    @ResponseStatus(HttpStatus.OK)
    public List<TreatmentDto> getTreatmentWithCurrencyByPatientId(@PathVariable(name = "id") Long id,
                                                                  @PathVariable(name = "currency") String currency,
                                                                  @PathVariable(name = "numberOfPage") int numberOfPage,
                                                                  @PathVariable(name = "countOfItems") int countOfItems) {
        try {
            List<TreatmentDto> allTreatmentsByPatientId = treatmentService.getAllTreatmentsByPatientId(id, currency, numberOfPage, countOfItems);
            if (allTreatmentsByPatientId.isEmpty()) {
                throw new ApiRequestExceptionTreatment(String.format(responseWithoutID, id));
            }
            return allTreatmentsByPatientId;
        } catch (ResourceAccessException e) {
            throw new ApiRequestExceptionTreatment("Sorry, but temporarily apps doesn't have access to up-to-date course exchange (USD,EUR,RUB). You should request UAH.");
        }

    }

    /**
     * @return all Treatments of history of hospital
     */
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "treatments sent"),
            @ApiResponse(responseCode = "404", description = "Don't have any treatments")
    })
    @Operation(summary = "get all treatments from hospital", description = "get all treatments from history of hospital")
    @GetMapping("/treatments")
    @ResponseStatus(HttpStatus.OK)
    public List<TreatmentDto> getAll() {
        List<TreatmentDto> allTreatments = treatmentService.getAllTreatments();

        if (allTreatments.isEmpty()) {
            throw new ApiRequestExceptionTreatment("don't have any treatments in DataBase");
        }
        return allTreatments;
    }

    /**
     * @param treatmentSaveDto - receive model "treatmentSaveDto" and save to DB.
     */
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "treatment created"),
            @ApiResponse(responseCode = "404", description = "treatment didn't create")
    })
    @Operation(summary = "create treatment", description = "create treatment with medicine and appointments or just treatments without medicine and appointments")
    @PostMapping("/treatments")
    @ResponseStatus(HttpStatus.CREATED)
    public void saveNewTreatment(@RequestBody TreatmentSaveDto treatmentSaveDto) {
        if (!treatmentService.saveTreatment(treatmentSaveDto)) {
            throw new ApiRequestExceptionTreatment(didntCreate);
        }
    }

    /**
     * @param id - receive id of treatment and delete treatment with this id.
     */
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "treatment deleted"),
    })
    @Operation(summary = "delete treatment", description = "delete treatment")
    @DeleteMapping("/treatments/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTreatmentById(@PathVariable(name = "id") Long id) {
        treatmentService.deleteById(id);
    }

    /**
     * @param id     receive id of treatment that will be updated by status
     * @param status receive type of status, just like that: PREPARING("in preparing"), IN_PROGRESS("in progress"), FINISHED("finished");
     */
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "treatment deleted"),
            @ApiResponse(responseCode = "404", description = "no such any treatments")
    })
    @Operation(summary = "delete treatment", description = "delete treatment")
    @PutMapping("treatments")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateTreatment(@RequestParam("id") Long id, @RequestParam(name = "status") String status) {
        treatmentService.updateTreatment(status, id);
    }

    /**
     * @param id         - demand PatientId
     * @param beforeDate - demand date than will be bigger than Treatment was started
     * @param afterDate  - demand date than will be bigger than Treatment was ended
     * @return - List of TreatmentEntity by PatientId and by during after "beforeDate" and before "afterDate";
     */
    @GetMapping("/treatments/{id}/{beforeDate}/{afterDate}/{numberOfPage}/{countOfItems}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "treatments sent"),
            @ApiResponse(responseCode = "404", description = "No such any treatments")})
    @Operation(summary = "get treatments by PatientId with range of time ", description = "get all treatments from patient history with range between beforeDate and фfterDate")
    @ResponseStatus(HttpStatus.OK)
    public List<TreatmentEntity> getTreatmentByRangeOfDateWithoutPages(@PathVariable(name = "id") Long id,
                                                                       @PathVariable(name = "beforeDate") String beforeDate,
                                                                       @PathVariable(name = "afterDate") String afterDate,
                                                                       @PathVariable(name = "numberOfPage") int numberOfPage,
                                                                       @PathVariable(name = "countOfItems") int countOfItems) {
        List<TreatmentEntity> allTreatmentsByPatientIdAndRangeDates = treatmentService.getAllTreatmentsByPatientIdAndRangeDates(LocalDate.parse(beforeDate), LocalDate.parse(afterDate), id, numberOfPage, countOfItems);
        if (allTreatmentsByPatientIdAndRangeDates.isEmpty()) {
            throw new ApiRequestExceptionTreatment("Sorry, but you don't have any treatments by your range of time");
        }
        return allTreatmentsByPatientIdAndRangeDates;
    }

    @GetMapping("/treatments/{id}/{dateFirst}/{dateSecond}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "treatments sent"),
            @ApiResponse(responseCode = "404", description = "No such any treatments")})
    @Operation(summary = "get treatments by PatientId with range of time ", description = "get all treatments from patient history with range between beforeDate and фfterDate")
    @ResponseStatus(HttpStatus.OK)
    public List<TreatmentEntity> getTreatmentByRangeOfDateWithoutPages(@PathVariable(name = "id") Long id,
                                                                       @PathVariable(name = "dateFirst") String dateFirst,
                                                                       @PathVariable(name = "dateSecond") String dateSecond
    ) {
        List<TreatmentEntity> allTreatmentsByPatientIdAndRangeDates = treatmentService.getAllTreatmentsByPatientIdAndRangeDates(LocalDate.parse(dateFirst), LocalDate.parse(dateSecond), id);
        if (allTreatmentsByPatientIdAndRangeDates.isEmpty()) {
            throw new ApiRequestExceptionTreatment("Sorry, but you don't have any treatments by your range of time with id" + id + ", or your range of date.");
        }
        return allTreatmentsByPatientIdAndRangeDates;
    }

    @GetMapping("/treatment/{patientId}/{doctorId}")
    public List<TreatmentDto> getFreshTreatments(@PathVariable(name = "patientId")Long patientId,@PathVariable(name = "doctorId") Long doctorId){
        return treatmentService.getAllTreatmentsByPatientIdAndDoctorId(patientId,doctorId);
    }
}
