package com.grid.dynamics.demoprojecthospital.exceptions;

/**
 * this class extended RuntimeException just Override method from RuntimeException.
 */
public class ApiRequestExceptionTreatment extends RuntimeException {

    public ApiRequestExceptionTreatment(String message) {
        super(message);
    }
}
