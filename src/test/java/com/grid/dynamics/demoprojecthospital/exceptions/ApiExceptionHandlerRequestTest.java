package com.grid.dynamics.demoprojecthospital.exceptions;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class ApiExceptionHandlerRequestTest {
    private final String text = "Something is going wrong";
    private final ApiExceptionHandlerRequest apiExceptionHandlerRequest = new ApiExceptionHandlerRequest();
    private final String patternTime = "dd.MM.yyyy HH:mm:ss";
    private final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(patternTime);


    @Test
    void ShouldHandleApiRequestException() {
        ApiRequestExceptionTreatment exception = new ApiRequestExceptionTreatment(text);
        ApiException apiException = new ApiException(text, HttpStatus.BAD_REQUEST, HttpStatus.BAD_REQUEST.value(), LocalDateTime.now().format(dateTimeFormatter));
        ResponseEntity<Object> actual = apiExceptionHandlerRequest.handleApiRequestException(exception);
        ResponseEntity expect = new ResponseEntity(apiException, HttpStatus.BAD_REQUEST);
        assertEquals(expect, actual);
    }
}