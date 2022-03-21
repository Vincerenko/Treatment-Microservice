package com.grid.dynamics.demoprojecthospital.services.api;

import com.ctc.wstx.shaded.msv_core.util.Uri;
import com.grid.dynamics.demoprojecthospital.adapter.PdmAdapter;
import com.grid.dynamics.demoprojecthospital.models.enums.PdmURL;
import com.grid.dynamics.demoprojecthospital.models.wrapper.AppointmentCalendarDto;
import com.grid.dynamics.demoprojecthospital.models.wrapper.AppointmentsCalendars;
import com.grid.dynamics.demoprojecthospital.models.wrapper.Doctor;
import com.grid.dynamics.demoprojecthospital.utils.HeaderService;
import io.swagger.v3.oas.annotations.headers.Header;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CalendarServiceApi {
    private String token = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJwb2RlbG9qNTE3QHNpYmVycGF5LmNvbSIsImF1dGhvcml0aWVzIjpbeyJhdXRob3JpdHkiOiJhY2NvdW50OnJlYWQifSx7ImF1dGhvcml0eSI6InBhdGllbnQ6Y3JlYXRlIn0seyJhdXRob3JpdHkiOiJwYXRpZW50OmRlbGV0ZSJ9LHsiYXV0aG9yaXR5IjoiYWNjb3VudDpjcmVhdGUifSx7ImF1dGhvcml0eSI6ImRvY3RvcjpjcmVhdGUifSx7ImF1dGhvcml0eSI6ImRvY3RvcjpkZWxldGUifSx7ImF1dGhvcml0eSI6ImFjY291bnQ6ZGVsZXRlIn0seyJhdXRob3JpdHkiOiJST0xFX0FETUlOIn0seyJhdXRob3JpdHkiOiJkb2N0b3I6dXBkYXRlIn0seyJhdXRob3JpdHkiOiJhY2NvdW50OnVwZGF0ZSJ9LHsiYXV0aG9yaXR5IjoicGF0aWVudDpyZWFkIn0seyJhdXRob3JpdHkiOiJkb2N0b3I6cmVhZCJ9XSwiaWF0IjoxNjQ3MzQ1NzEwLCJleHAiOjE2NDg1MDEyMDB9.JVs8RDH5GvqMg9k4HQsf26D-FqDerdf39PvuN8jCPCTIjWlI0qqZvnQFukTDXOTCfvl-mLkNbVqBZzOIIN6nLA";
    private final RestTemplate restTemplate;
    private final HeaderService headerService;
    private final String url = "https://appointment-management-api.herokuapp.com/calendar/outdated?doctorId=%d&patientId=%d";

    public ResponseEntity<AppointmentCalendarDto[]> getAppointmentFromServer(Long patientId, Long doctorId) {
        HttpEntity httpEntity = new HttpEntity(headerService.getToken());
        return restTemplate.exchange(String.format(url, doctorId, patientId), HttpMethod.GET, httpEntity, AppointmentCalendarDto[].class);
    }








}
