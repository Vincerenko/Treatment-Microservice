package com.grid.dynamics.demoprojecthospital.controller;

import com.grid.dynamics.demoprojecthospital.exceptions.ApiRequestExceptionTreatment;
import com.grid.dynamics.demoprojecthospital.models.Medicine;
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

    private final MedicineService medicineService;

    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "medicine created"),
            @ApiResponse(responseCode = "404", description = "medicine didn't create")
    })
    @Operation(summary = "create medicine", description = "create medicine and calc sum which will be connected to treatment by treatment_id")
    @PostMapping("/medicine/{treatmentId}")
    @ResponseStatus(HttpStatus.CREATED)
    public void saveNewMedicine(@RequestBody Medicine medicine, @PathVariable(name = "treatmentId") Long treatmentId) {
        try {
            medicineService.saveCustomMedicine(medicine, treatmentId);
        } catch (RuntimeException e) {
            throw new ApiRequestExceptionTreatment("Something is going wrong, medicine wasn't saved, maybe your medicine.treatment_id doesn't match with main treatment.id");
        }
    }

    @PostMapping("/medicines/{treatId}")
    @ResponseStatus(HttpStatus.CREATED)
    public void saveListNewMedicine(@RequestBody List<Medicine> medicine, @PathVariable(name = "treatId") Long treatId) {
        try {
            medicineService.saveArrayMedicine(medicine, treatId);
        } catch (RuntimeException e) {
            throw new ApiRequestExceptionTreatment("Something is going wrong, medicine wasn't saved, maybe your medicine.treatment_id doesn't match with main treatment.id");
        }
    }

    @PostMapping("/medicines/{treatmentId}/{medicineId}")
    @ResponseStatus(HttpStatus.CREATED)
    public void saveListNewMedicine(@PathVariable(name = "treatmentId") Long treatmentId, @PathVariable(name = "medicineId") Long medicineId) {
        try {
            medicineService.saveByMedicineIdAndTreatmentId(treatmentId, medicineId);
        } catch (RuntimeException e) {
            throw new ApiRequestExceptionTreatment("Something is going wrong, medicine wasn't saved, maybe your medicine.treatment_id doesn't match with main treatment.id");
        }
    }


}
