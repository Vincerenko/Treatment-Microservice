package com.grid.dynamics.demoprojecthospital.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


/**
 * This class describe main logic of custom class-exception
 */
@ControllerAdvice
public class ApiExceptionHandlerRequest {
    private final String patternTime = "dd.MM.yyyy HH:mm:ss";
    private final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(patternTime);

//    @ExceptionHandler(value = {ApiRequestExceptionTreatment.class})
//    public ResponseEntity<Object> handleApiRequestException(ApiRequestExceptionTreatment e) {
//        HttpStatus statusBadRequest = HttpStatus.BAD_REQUEST;
//        ApiException apiException = new ApiException(
//                e.getMessage(),
//                statusBadRequest,
//                LocalDateTime.now().format(dateTimeFormatter));
//        return new ResponseEntity<>(apiException, statusBadRequest);
//    }

    @ExceptionHandler(value = {ApiRequestExceptionTreatment.class})
    public ResponseEntity <Object>handleApiRequestException(ApiRequestExceptionTreatment e) {
        HttpStatus status;
        if (e.getHttpStatus() == null) {
            status = HttpStatus.BAD_REQUEST;
        }
        else {
             status = e.getHttpStatus();
        }
        ApiException apiException = new ApiException(
                e.getMessage(),
                status,
                status.value(),
                LocalDateTime.now().format(dateTimeFormatter)
                );
        return new ResponseEntity<>(apiException, status);
    }
}
