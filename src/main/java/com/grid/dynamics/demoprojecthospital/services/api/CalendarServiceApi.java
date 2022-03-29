package com.grid.dynamics.demoprojecthospital.services.api;

import com.grid.dynamics.demoprojecthospital.adapter.Adapter;
import com.grid.dynamics.demoprojecthospital.models.wrapper.AppointmentCalendarDto;
import com.grid.dynamics.demoprojecthospital.services.HeaderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

@Service
@RequiredArgsConstructor
public class CalendarServiceApi {
    private final RestTemplate restTemplate;
    private final HeaderService headerService;
    private final Adapter adapter;
    private final String url = "https://appointment-management-api.herokuapp.com/calendar/outdated?doctorId=%d&patientId=%d";
    // private final String token = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJqdWxpYXNoa2E1MjM4MDY3NTcxMDkyNkBnbWFpbC5jb20iLCJhdXRob3JpdGllcyI6W3siYXV0aG9yaXR5IjoiYWNjb3VudDpyZWFkIn0seyJhdXRob3JpdHkiOiJwYXRpZW50OmNyZWF0ZSJ9LHsiYXV0aG9yaXR5IjoicGF0aWVudDpkZWxldGUifSx7ImF1dGhvcml0eSI6ImFjY291bnQ6Y3JlYXRlIn0seyJhdXRob3JpdHkiOiJkb2N0b3I6ZGVsZXRlIn0seyJhdXRob3JpdHkiOiJkb2N0b3I6Y3JlYXRlIn0seyJhdXRob3JpdHkiOiJhY2NvdW50OmRlbGV0ZSJ9LHsiYXV0aG9yaXR5IjoiUk9MRV9BRE1JTiJ9LHsiYXV0aG9yaXR5IjoiYWNjb3VudDp1cGRhdGUifSx7ImF1dGhvcml0eSI6ImRvY3Rvcjp1cGRhdGUifSx7ImF1dGhvcml0eSI6ImRvY3RvcjpyZWFkIn0seyJhdXRob3JpdHkiOiJwYXRpZW50OnJlYWQifV0sImlhdCI6MTY0ODA1ODg3MCwiZXhwIjoxNjQ5MjAzMjAwfQ.spq6IJ1jk5GoL9G9PBrfb6OUzJT1cNQiAn_feVkaX4Js0750g0HJDL8uclngpmotETTte-eg8dnAXm6fx3DWug";

//    public ResponseEntity<AppointmentCalendarDto[]> getAppointmentFromServer(Long patientId, Long doctorId) {
//        HttpEntity httpEntity = new HttpEntity(headerService.getToken());
//        return restTemplate.exchange(String.format(url, doctorId, patientId), HttpMethod.GET, httpEntity, AppointmentCalendarDto[].class);
//    }

    public ResponseEntity<AppointmentCalendarDto[]> getAppointmentFromServer(Long patientId, Long doctorId) {
        ResponseEntity<AppointmentCalendarDto[]> responseEntity = adapter.getResponseEntity("https://appointment-management-api.herokuapp.com/", headerService.getToken(), String.format("calendar/outdated?doctorId=%d&patientId=%d", doctorId, patientId), null, AppointmentCalendarDto[].class);
        System.out.println(Arrays.toString(responseEntity.getBody()));
        return responseEntity;

    }


}
