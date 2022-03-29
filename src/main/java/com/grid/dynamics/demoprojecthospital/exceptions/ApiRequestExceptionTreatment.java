package com.grid.dynamics.demoprojecthospital.exceptions;

import org.springframework.http.HttpStatus;

/**
 * this class extended RuntimeException just Override method from RuntimeException.
 */
public class ApiRequestExceptionTreatment extends RuntimeException {
    private HttpStatus httpStatus;
    public ApiRequestExceptionTreatment(String message) {
        super(message);
    }

    public ApiRequestExceptionTreatment(String message, HttpStatus httpStatus) {
        super(message);
        this.httpStatus=httpStatus;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
}
