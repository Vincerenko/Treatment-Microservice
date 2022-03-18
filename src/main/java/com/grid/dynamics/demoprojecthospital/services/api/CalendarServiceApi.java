package com.grid.dynamics.demoprojecthospital.services.api;

import com.grid.dynamics.demoprojecthospital.dto.AppointmentCalendarDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CalendarServiceApi {
    private final RestTemplate restTemplate;
    private final String url = "https://appointment-management-api.herokuapp.com/calendar/outdated?doctorId=%d&patientId=%d";

    public List<AppointmentCalendarDto> getAppointmentFromServer(Long patientId, Long doctorId) {
        return restTemplate.getForObject(String.format(url, doctorId, patientId), ArrayList.class);
    }


}
