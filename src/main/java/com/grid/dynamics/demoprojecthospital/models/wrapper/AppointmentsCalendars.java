package com.grid.dynamics.demoprojecthospital.models.wrapper;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class AppointmentsCalendars {
    private List<AppointmentCalendarDto> appointmentCalendarDtos;
}

