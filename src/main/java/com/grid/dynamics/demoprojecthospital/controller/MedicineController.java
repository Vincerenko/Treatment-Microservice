package com.grid.dynamics.demoprojecthospital.controller;

import com.grid.dynamics.demoprojecthospital.exceptions.ApiRequestExceptionTreatment;
import com.grid.dynamics.demoprojecthospital.models.Medicine;
import com.grid.dynamics.demoprojecthospital.models.enums.UserRole;
import com.grid.dynamics.demoprojecthospital.services.AuthService;
import com.grid.dynamics.demoprojecthospital.services.MedicineService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1")
@Transactional
@RequiredArgsConstructor
public class MedicineController {
    private final String noSave = "Something is going wrong, medicine wasn't saved.";
    private final String wrongVerification = "Sorry, but you dont have access to this page";
    private final MedicineService medicineService;
    private final AuthService authService;

    /**
     *
     * @param medicine receive object medicine
     * @param treatmentId receive id of treatment that will be connected to each other
     */
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "medicine created"),
            @ApiResponse(responseCode = "404", description = "medicine didn't create")
    })
    @Operation(summary = "Save medicine", description = "Save medicine and calc sum which will be connected to treatment by treatment_id")

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/medicine/{treatmentId}")
    public void saveNewMedicine(@RequestBody Medicine medicine, @PathVariable(name = "treatmentId") Long treatmentId) {
        if (authService.verifyRole(UserRole.DOCTOR, UserRole.ADMIN)) {
            try {
                medicineService.saveCustomMedicine(medicine, treatmentId);
            } catch (RuntimeException e) {
                throw new ApiRequestExceptionTreatment(noSave);
            }
        }
        else {
            throw new ApiRequestExceptionTreatment(wrongVerification);
        }
    }

    /**
     *
     * @param medicine receive object medicine
     * @param treatId receive id of treatment that will be connected to each other
     */
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "medicine created"),
            @ApiResponse(responseCode = "404", description = "medicine didn't create")
    })
    @Operation(summary = "Save medicine", description = "Save list of medicines and calc sum which will be connected to treatment by treatment_id")

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/medicines/{treatId}")
    public void saveNewMedicineByBothIds(@RequestBody List<Medicine> medicine, @PathVariable(name = "treatId") Long treatId) {
        if (authService.verifyRole(UserRole.DOCTOR, UserRole.ADMIN)) {
            try {
                medicineService.saveArrayMedicine(medicine, treatId);
            } catch (RuntimeException e) {
                throw new ApiRequestExceptionTreatment(noSave);
            }
        }
        else {
            throw new ApiRequestExceptionTreatment(wrongVerification);
        }
    }

    /**
     *
     * @param treatmentId receive id of treatment that already exists in database
     * @param medicineId receive id of medicine that you want to paste id treatment
     */
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "medicine created"),
            @ApiResponse(responseCode = "404", description = "medicine didn't create")
    })
    @Operation(summary = "Save medicine", description = "Save medicine by id medicine and id treatment and calc sum which will be connected to treatment by treatment_id")

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/medicines/{treatmentId}/{medicineId}")
    public void saveNewMedicineByBothIds(@PathVariable(name = "treatmentId") Long treatmentId, @PathVariable(name = "medicineId") Long medicineId) {
        if (authService.verifyRole(UserRole.DOCTOR, UserRole.ADMIN)) {
            try {
                medicineService.saveByMedicineIdAndTreatmentId(treatmentId, medicineId);
            } catch (RuntimeException e) {
                throw new ApiRequestExceptionTreatment(noSave);
            }
        }
        else {
            throw new ApiRequestExceptionTreatment(wrongVerification);
        }
    }


}
