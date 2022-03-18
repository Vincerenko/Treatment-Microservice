package com.grid.dynamics.demoprojecthospital.controller;

import com.grid.dynamics.demoprojecthospital.adapter.PdmAdapter;
import com.grid.dynamics.demoprojecthospital.models.enums.PdmURL;
import com.grid.dynamics.demoprojecthospital.models.pdmModel.Doctor;
import com.grid.dynamics.demoprojecthospital.services.api.PdmPatientApi;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "api/v1")
public class PdmController {
    private final PdmPatientApi pdmPatientApi;
    private final PdmAdapter pdmAdapter;
    private String token = "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJwb2RlbG9qNTE3QHNpYmVycGF5LmNvbSIsImF1dGhvcml0aWVzIjpbeyJhdXRob3JpdHkiOiJhY2NvdW50OnJlYWQifSx7ImF1dGhvcml0eSI6InBhdGllbnQ6Y3JlYXRlIn0seyJhdXRob3JpdHkiOiJwYXRpZW50OmRlbGV0ZSJ9LHsiYXV0aG9yaXR5IjoiYWNjb3VudDpjcmVhdGUifSx7ImF1dGhvcml0eSI6ImRvY3RvcjpjcmVhdGUifSx7ImF1dGhvcml0eSI6ImRvY3RvcjpkZWxldGUifSx7ImF1dGhvcml0eSI6ImFjY291bnQ6ZGVsZXRlIn0seyJhdXRob3JpdHkiOiJST0xFX0FETUlOIn0seyJhdXRob3JpdHkiOiJkb2N0b3I6dXBkYXRlIn0seyJhdXRob3JpdHkiOiJhY2NvdW50OnVwZGF0ZSJ9LHsiYXV0aG9yaXR5IjoicGF0aWVudDpyZWFkIn0seyJhdXRob3JpdHkiOiJkb2N0b3I6cmVhZCJ9XSwiaWF0IjoxNjQ3MzQ1NzEwLCJleHAiOjE2NDg1MDEyMDB9.JVs8RDH5GvqMg9k4HQsf26D-FqDerdf39PvuN8jCPCTIjWlI0qqZvnQFukTDXOTCfvl-mLkNbVqBZzOIIN6nLA";

    @GetMapping("/doctor/{doctorId}")
    public Doctor getDoctorFromPdmServer(@PathVariable(name = "doctorId") Long doctorId){

        return pdmAdapter.getResponseEntity(token, PdmURL.GET_DOCTOR,null,Doctor.class,doctorId).getBody();
    }
}
