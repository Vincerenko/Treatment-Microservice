package com.grid.dynamics.demoprojecthospital.configuration;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class RestTemplateConfigurationTest {
    @Autowired
    private RestTemplateConfiguration restTemplateConfiguration = new RestTemplateConfiguration();

    @Test
    void ShouldCheckClassRestTemplate() {
        RestTemplate expected = new RestTemplate();
        RestTemplate actual = restTemplateConfiguration.restTemplate();
        assertEquals(expected.getClass(), actual.getClass());
    }
}