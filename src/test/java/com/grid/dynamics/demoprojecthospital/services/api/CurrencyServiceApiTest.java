package com.grid.dynamics.demoprojecthospital.services.api;

import com.grid.dynamics.demoprojecthospital.models.Currency;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.time.ZoneId;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class CurrencyServiceApiTest {
    private final String date = LocalDate.now(ZoneId.of("UTC")).toString().replace("-", "").trim();
    @Mock
    private RestTemplate restTemplate;
    @InjectMocks
    private CurrencyServiceApi currencyServiceApi;

    @Test
    void ShouldGetUSD() {
        Currency[] expected = {new Currency(23, "RUB", 28.00D, "rub", "20220215")};
        Mockito.when(restTemplate.getForObject("null" + date, Currency[].class)).thenReturn(expected);
        Currency[] actual = currencyServiceApi.getUSD();
        assertEquals(expected, actual);
    }

    @Test
    void ShouldGetEUR() {
        Currency[] expected = {new Currency(23, "RUB", 28.00D, "rub", "20220215")};
        Mockito.when(restTemplate.getForObject("null" + date, Currency[].class)).thenReturn(expected);
        Currency[] actual = currencyServiceApi.getEUR();
        assertEquals(expected, actual);
    }

    @Test
    void ShouldGetRUB() {
        Currency[] expected = {new Currency(23, "RUB", 28.00D, "rub", "20220215")};
        Mockito.when(restTemplate.getForObject("null" + date, Currency[].class)).thenReturn(expected);
        Currency[] actual = currencyServiceApi.getRUB();
        assertEquals(expected, actual);
    }
}