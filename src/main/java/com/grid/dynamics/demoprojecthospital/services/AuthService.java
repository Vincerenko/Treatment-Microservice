package com.grid.dynamics.demoprojecthospital.services;

import com.grid.dynamics.demoprojecthospital.adapter.PdmAdapter;
import com.grid.dynamics.demoprojecthospital.models.enums.MicroserviceURLS;
import com.grid.dynamics.demoprojecthospital.models.enums.PdmEndpoints;
import com.grid.dynamics.demoprojecthospital.models.enums.UserRole;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final PdmAdapter pdmAdapter;
    private final HeaderService headerService;

    public boolean verifyRole(UserRole expectedRole){
        ResponseEntity<String> result =
                pdmAdapter.getResponseEntity(MicroserviceURLS.PDM, headerService.getToken(), PdmEndpoints.GET_ROLE_BY_TOKEN,null,String.class);
        System.out.println(result.getBody());
        System.out.println(expectedRole.getRole());
        return expectedRole.getRole().equals(result.getBody());
    }
}