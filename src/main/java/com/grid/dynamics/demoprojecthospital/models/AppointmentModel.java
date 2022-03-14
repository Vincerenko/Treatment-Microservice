package com.grid.dynamics.demoprojecthospital.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@AllArgsConstructor
@Getter
@JsonDeserialize(builder = AppointmentModel.Builder.class)
@JsonIgnoreProperties(ignoreUnknown = true)
@EqualsAndHashCode
public class AppointmentModel {
    private int id;
    private String title;
    private Integer doctorId;
    private Integer patientId;
    private LocalDate date;
    private LocalTime fromTime;
    private LocalTime toTime;
    private String summary;

    @Setter
    @Getter
    @JsonPOJOBuilder
    public static class Builder {
        private int id;
        private String title;
        private Integer doctorId;
        private Integer patientId;
        private LocalDate date;
        private LocalTime fromTime;
        private LocalTime toTime;
        private String summary;

        public AppointmentModel build() {
            return new AppointmentModel(id, title, doctorId, patientId, date, fromTime, toTime, summary);
        }
    }
}