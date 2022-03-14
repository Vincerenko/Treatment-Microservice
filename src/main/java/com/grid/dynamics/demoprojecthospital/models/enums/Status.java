package com.grid.dynamics.demoprojecthospital.models.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * This Enum describe variants of status of treatment.
 */
@AllArgsConstructor
@Getter
public enum Status {
    PREPARING("in preparing"), IN_PROGRESS("in progress"), FINISHED("finished");
    private final String status;
}
