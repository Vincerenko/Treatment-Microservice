package com.grid.dynamics.demoprojecthospital.controller;

import com.grid.dynamics.demoprojecthospital.dto.TreatmentDto;
import com.grid.dynamics.demoprojecthospital.dto.TreatmentSaveDto;
import com.grid.dynamics.demoprojecthospital.exceptions.ApiRequestExceptionTreatment;
import com.grid.dynamics.demoprojecthospital.models.TreatmentEntity;
import com.grid.dynamics.demoprojecthospital.models.enums.UserRole;
import com.grid.dynamics.demoprojecthospital.services.AuthService;
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
    private final String wrongVerification = "Sorry, but you dont have access to this page";
    private final String dontHaveCurrencyExchange = "Sorry, but temporarily application doesn't have access to up-to-date course exchange (USD,EUR,RUB). You should request UAH.";
    private final String notFoundTreatments = "No such any treatments.";
    private final AuthService authService;
    private final TreatmentService treatmentService;

    /**
     * @param id           is demanding id of Patient.
     * @param numberOfPage number of page which will contain
     * @param countOfItems this parameter responsible for the numbers of items on page
     * @return all Treatments of received id of Patient.
     * This method receive id of Patient and return all treatments that patient with this id has.
     */

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Treatments were sent"),
            @ApiResponse(responseCode = "404", description = "No such any treatments.")})
    @Operation(summary = "Get treatments by patient id",
            description = "Get all treatments from patient history with currency UAH and with pageable")

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/treatments/")
    public List<TreatmentDto> getTreatment(@RequestParam Long id,
                                           @RequestParam int numberOfPage,
                                           @RequestParam int countOfItems) {
        if (authService.verifyRole(UserRole.ADMIN, UserRole.DOCTOR, UserRole.PATIENT)) {
            try {
                return treatmentService.getAllTreatmentsByPatientId(id, numberOfPage, countOfItems);
            }
            catch (RuntimeException e){
                throw new ApiRequestExceptionTreatment(String.format(responseWithoutID, id));
            }
        }
        else {
            throw new ApiRequestExceptionTreatment(wrongVerification);
        }
    }

    /**
     * @param id       is demanding id of Patient.
     * @param currency receive type of currency, after that will be returned result price in currency that was requested (receive only: "USD", "EUR","RUB","UAH")
     * @return all Treatments of received id of Patient with needed currency.
     */
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "treatments sent"),
            @ApiResponse(responseCode = "404", description = "No such any treatments.")})
    @Operation(summary = "Get treatments by id with currency", description = "Get all treatments from patient history with currency USD, RUB or EUR without pageable")

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/treatments/{id}/{currency}")
    public List<TreatmentDto> getTreatmentWithCurrencyByPatientId(@PathVariable(name = "id") Long id, @PathVariable(name = "currency") String currency) {
        if (authService.verifyRole(UserRole.ADMIN, UserRole.DOCTOR, UserRole.PATIENT)) {
            try {
                List<TreatmentDto> allTreatmentsByPatientId = treatmentService.getAllTreatmentsByPatientId(id, currency);
                if (allTreatmentsByPatientId.isEmpty()) {
                    throw new ApiRequestExceptionTreatment(String.format(responseWithoutID, id));
                }
                return allTreatmentsByPatientId;
            } catch (ResourceAccessException e) {
                throw new ApiRequestExceptionTreatment(dontHaveCurrencyExchange);
            }
        }
        throw new ApiRequestExceptionTreatment(wrongVerification);
    }

    /**
     * @param id           patientId
     * @param currency     type of currency, client can write "USD", "RUB" or "EUR" or native currency "UAH"
     * @param numberOfPage this variable responsible for number of page
     * @param countOfItems this variable responsible for volume of items on page
     * @return list of treatments by patientId with requested currency and pageable
     */
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "treatments sent"),
            @ApiResponse(responseCode = "404", description = "No such any treatments.")})
    @Operation(summary = "Get treatments by id with currency", description = "Get all treatments from patient history with currency USD, RUB or EUR and pageable")

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/treatments/{id}/{currency}/{numberOfPage}/{countOfItems}")
    public List<TreatmentDto> getTreatmentWithCurrencyByPatientId(@PathVariable(name = "id") Long id,
                                                                  @PathVariable(name = "currency") String currency,
                                                                  @PathVariable(name = "numberOfPage") int numberOfPage,
                                                                  @PathVariable(name = "countOfItems") int countOfItems) {
        if (authService.verifyRole(UserRole.ADMIN, UserRole.DOCTOR, UserRole.PATIENT)) {
            try {
                List<TreatmentDto> allTreatmentsByPatientId = treatmentService.getAllTreatmentsByPatientId(id, currency, numberOfPage, countOfItems);
                if (allTreatmentsByPatientId.isEmpty()) {
                    throw new ApiRequestExceptionTreatment(String.format(responseWithoutID, id));
                }
                return allTreatmentsByPatientId;
            } catch (ResourceAccessException e) {
                throw new ApiRequestExceptionTreatment(dontHaveCurrencyExchange);
            }
        }
        throw new ApiRequestExceptionTreatment(wrongVerification);
    }

    /**
     * @return all Treatments of hospital history
     */
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "treatments sent"),
            @ApiResponse(responseCode = "404", description = "No such any treatments.")
    })
    @Operation(summary = "Get all treatments from hospital", description = "Get all treatments from history of hospital")

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/treatments")
    public List<TreatmentDto> getAll() {
        if (authService.verifyRole(UserRole.ADMIN)) {
            if (treatmentService.getAllTreatments().isEmpty()) {
                throw new ApiRequestExceptionTreatment(notFoundTreatments);
            }
            return treatmentService.getAllTreatments();
        }
        throw new ApiRequestExceptionTreatment(wrongVerification);
    }

    /**
     * @param treatmentSaveDto - receive model "treatmentSaveDto" and save to DB.
     */
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Treatment created"),
            @ApiResponse(responseCode = "404", description = "Treatment didn't create")
    })
    @Operation(summary = "Create treatment", description = "Create treatment with medicine and appointments or just treatments without medicine and appointments")

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/treatments")
    public void saveNewTreatment(@RequestBody TreatmentSaveDto treatmentSaveDto) {
        if (authService.verifyRole(UserRole.DOCTOR, UserRole.ADMIN)) {
            if (!treatmentService.saveTreatment(treatmentSaveDto)) {
                throw new ApiRequestExceptionTreatment(didntCreate);
            }
        } else {
            throw new ApiRequestExceptionTreatment(wrongVerification);
        }
    }

    /**
     * @param id - receive id of treatment and delete treatment with this id.
     */
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Treatment deleted"),
            @ApiResponse(responseCode = "404", description = "Treatment didn't delete")
    })
    @Operation(summary = "Delete treatment", description = "Delete treatment by treatmentId")

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/treatments/{id}")
    public void deleteTreatmentById(@PathVariable(name = "id") Long id) {
        if (authService.verifyRole(UserRole.DOCTOR, UserRole.ADMIN)) {
            treatmentService.deleteById(id);
        }
        else {
            throw new ApiRequestExceptionTreatment(wrongVerification);
        }
    }

    /**
     * @param id     receive id of treatment that will be updated by status
     * @param status receive type of status, just like that: PREPARING("in preparing"), IN_PROGRESS("in progress"), FINISHED("finished");
     */
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Treatment status was updated"),
            @ApiResponse(responseCode = "404", description = "Treatment status wasn't updated")
    })
    @Operation(summary = "Update treatment", description = "Update status treatment by treatmentId")

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("treatments")
    public void updateTreatment(@RequestParam("id") Long id, @RequestParam(name = "status") String status) {
        if (authService.verifyRole(UserRole.DOCTOR, UserRole.ADMIN)) {
            treatmentService.updateTreatment(status, id);
        }
        else {
            throw new ApiRequestExceptionTreatment(wrongVerification);
        }
    }

    /**
     * @param id           - demand PatientId
     * @param beforeDate   - demand date than will be bigger than Treatment was started
     * @param afterDate    - demand date than will be bigger than Treatment was ended
     * @param numberOfPage this variable responsible for number of page
     * @param countOfItems this variable responsible for volume of items on page
     * @return - List of TreatmentEntity by PatientId and by during after "beforeDate" and before "afterDate";
     */

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Treatments sent"),
            @ApiResponse(responseCode = "404", description = "No such any treatments")})
    @Operation(summary = "Get treatments by PatientId with range of time ", description = "Get all treatments from patient history with range between beforeDate and afterDate and with pageable")

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/treatments/{id}/{beforeDate}/{afterDate}/{numberOfPage}/{countOfItems}")
    public List<TreatmentEntity> getTreatmentByRangeOfDateWithoutPages(@PathVariable(name = "id") Long id,
                                                                       @PathVariable(name = "beforeDate") String beforeDate,
                                                                       @PathVariable(name = "afterDate") String afterDate,
                                                                       @PathVariable(name = "numberOfPage") int numberOfPage,
                                                                       @PathVariable(name = "countOfItems") int countOfItems) {
        if (authService.verifyRole(UserRole.DOCTOR, UserRole.ADMIN,UserRole.PATIENT)) {
            if (treatmentService.getAllTreatmentsByPatientIdAndRangeDates(LocalDate.parse(beforeDate), LocalDate.parse(afterDate), id, numberOfPage, countOfItems).isEmpty()) {
                throw new ApiRequestExceptionTreatment(notFoundTreatments);
            }
            return treatmentService.getAllTreatmentsByPatientIdAndRangeDates(LocalDate.parse(beforeDate), LocalDate.parse(afterDate), id, numberOfPage, countOfItems);
        }
        throw new ApiRequestExceptionTreatment(wrongVerification);
    }


    /**
     *
     * @param id - demand PatientId
     * @param dateFirst - demand date than will be bigger than Treatment was started
     * @param dateSecond - demand date than will be bigger than Treatment was ended
     * @return - List of TreatmentEntity by PatientId and by during after "beforeDate" and before "afterDate";
     */
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Treatments were sent"),
            @ApiResponse(responseCode = "404", description = "No such any treatments")})
    @Operation(summary = "Get treatments by PatientId with range of time ", description = "Get all treatments from patient history with range between beforeDate and afterDate without pageable.")

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/treatments/{id}/{dateFirst}/{dateSecond}")
    public List<TreatmentEntity> getTreatmentByRangeOfDateWithoutPages(@PathVariable(name = "id") Long id,
                                                                       @PathVariable(name = "dateFirst") String dateFirst,
                                                                       @PathVariable(name = "dateSecond") String dateSecond) {
        if (authService.verifyRole(UserRole.DOCTOR, UserRole.ADMIN,UserRole.PATIENT)) {
            List<TreatmentEntity> allTreatmentsByPatientIdAndRangeDates = treatmentService.getAllTreatmentsByPatientIdAndRangeDates(LocalDate.parse(dateFirst), LocalDate.parse(dateSecond), id);
            if (allTreatmentsByPatientIdAndRangeDates.isEmpty()) {
                throw new ApiRequestExceptionTreatment(notFoundTreatments);
            }
            return allTreatmentsByPatientIdAndRangeDates;
        }
        throw new ApiRequestExceptionTreatment(wrongVerification);
    }

    /**
     *
     * @param patientId demand patientId
     * @param doctorId demand doctorId
     * @return treatment with up-to-date appointments
     */
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Treatment was updated"),
            @ApiResponse(responseCode = "404", description = "No such any treatments")})
    @Operation(summary = "Get up-to-date treatment by patientId and doctorId",
            description = "Get treatment from patient history with range between beforeDate and afterDate without pageable and with up-to-date appointments.")

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/treatment/{patientId}/{doctorId}")
    public List<TreatmentEntity> getFreshTreatments(@PathVariable(name = "patientId") Long patientId, @PathVariable(name = "doctorId") Long doctorId) {
        if (authService.verifyRole(UserRole.DOCTOR, UserRole.ADMIN,UserRole.PATIENT)) {
            return treatmentService.getAllTreatmentsByPatientIdAndDoctorId(patientId, doctorId);
        }
        throw new ApiRequestExceptionTreatment(wrongVerification);
    }
}
