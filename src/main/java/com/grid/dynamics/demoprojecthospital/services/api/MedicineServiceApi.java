package com.grid.dynamics.demoprojecthospital.services.api;

import com.grid.dynamics.demoprojecthospital.models.wrapper.MedicineDto;
import com.grid.dynamics.demoprojecthospital.services.HeaderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class MedicineServiceApi {
    private final RestTemplate restTemplate;
    private final HeaderService headerService;
    private final String url = "https://medicine-management.herokuapp.com/medicines";
    private final String urlGetById = "https://medicine-management.herokuapp.com/medicines/%d";
    private String token = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJwb2RlbG9qNTE3QHNpYmVycGF5LmNvbSIsImF1dGhvcml0aWVzIjpbeyJhdXRob3JpdHkiOiJhY2NvdW50OnJlYWQifSx7ImF1dGhvcml0eSI6InBhdGllbnQ6Y3JlYXRlIn0seyJhdXRob3JpdHkiOiJwYXRpZW50OmRlbGV0ZSJ9LHsiYXV0aG9yaXR5IjoiYWNjb3VudDpjcmVhdGUifSx7ImF1dGhvcml0eSI6ImRvY3RvcjpjcmVhdGUifSx7ImF1dGhvcml0eSI6ImRvY3RvcjpkZWxldGUifSx7ImF1dGhvcml0eSI6ImFjY291bnQ6ZGVsZXRlIn0seyJhdXRob3JpdHkiOiJST0xFX0FETUlOIn0seyJhdXRob3JpdHkiOiJkb2N0b3I6dXBkYXRlIn0seyJhdXRob3JpdHkiOiJhY2NvdW50OnVwZGF0ZSJ9LHsiYXV0aG9yaXR5IjoicGF0aWVudDpyZWFkIn0seyJhdXRob3JpdHkiOiJkb2N0b3I6cmVhZCJ9XSwiaWF0IjoxNjQ3MzQ1NzEwLCJleHAiOjE2NDg1MDEyMDB9.JVs8RDH5GvqMg9k4HQsf26D-FqDerdf39PvuN8jCPCTIjWlI0qqZvnQFukTDXOTCfvl-mLkNbVqBZzOIIN6nLA";

    public ResponseEntity<MedicineDto[]> getMedicineFromServer() {
        HttpEntity httpEntity = new HttpEntity(headerService.getToken());
        return restTemplate.exchange(url, HttpMethod.GET, httpEntity, MedicineDto[].class);
    }

    public MedicineDto getMedicineById(Long id) {
        HttpEntity httpEntity = new HttpEntity(headerService.getToken());
        ResponseEntity<MedicineDto> exchange = restTemplate.exchange(String.format(urlGetById, id), HttpMethod.GET, httpEntity, MedicineDto.class);
        return exchange.getBody();
    }
}
