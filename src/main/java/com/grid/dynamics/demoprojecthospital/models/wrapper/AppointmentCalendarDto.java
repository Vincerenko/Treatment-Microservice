package com.grid.dynamics.demoprojecthospital.models.wrapper;

import com.grid.dynamics.demoprojecthospital.models.enums.Type;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AppointmentCalendarDto {
    private long id;
    private String title;
    private Long doctorId;
    private Long patientId;
    private LocalDate date;
    private LocalTime fromTime;
    private LocalTime toTime;
    private double cost;
    private Type type;
    private LocalDateTime creationDateTime;
    private String summary;

}
