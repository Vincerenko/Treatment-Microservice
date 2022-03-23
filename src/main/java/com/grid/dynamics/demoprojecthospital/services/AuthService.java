package com.grid.dynamics.demoprojecthospital.services;

import com.grid.dynamics.demoprojecthospital.adapter.PdmAdapter;
import com.grid.dynamics.demoprojecthospital.models.enums.MicroserviceURLS;
import com.grid.dynamics.demoprojecthospital.models.enums.PdmEndpoints;
import com.grid.dynamics.demoprojecthospital.models.enums.UserRole;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthService {
    private final PdmAdapter pdmAdapter;
    private final HeaderService headerService;

    public boolean verifyRole(UserRole... expectedRole) {
        ResponseEntity<String> response =
                pdmAdapter.getResponseEntity(MicroserviceURLS.PDM, headerService.getToken(), PdmEndpoints.GET_ROLE_BY_TOKEN, null, String.class);
        String role = response.getBody();
        log.info("Come role is : {}",response.getBody());
        if(role == null) {
            return false;
        }
        boolean result = false;
        for(UserRole actual : expectedRole){
            if(actual.getRole().equals(role)) {
                result = true;
                break;
            }
        }
        return result;
    }
}