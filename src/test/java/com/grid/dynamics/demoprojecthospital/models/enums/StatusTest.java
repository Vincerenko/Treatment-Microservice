package com.grid.dynamics.demoprojecthospital.models.enums;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class StatusTest {

    @Test
    void ShouldGetStatusFinished() {
        String expected = "finished";
        String actual = Status.FINISHED.getStatus();
        assertEquals(expected, actual);
    }

    @Test
    void ShouldGetStatusInPreparing() {
        String expected = "in preparing";
        String actual = Status.PREPARING.getStatus();
        assertEquals(expected, actual);
    }

    @Test
    void ShouldGetStatusInProgress() {
        String expected = "in progress";
        String actual = Status.IN_PROGRESS.getStatus();
        assertEquals(expected, actual);
    }
}