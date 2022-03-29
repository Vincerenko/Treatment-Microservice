package com.grid.dynamics.demoprojecthospital.exceptions;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.springframework.http.HttpStatus;


/**
 * This class describe fields that will have custom class with exception.
 */
@Getter
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class ApiException {
    private final String message;
    private final HttpStatus httpStatus;
    private final int code;
    private final String zonedDateTime;



}
