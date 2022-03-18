package com.grid.dynamics.demoprojecthospital.services.api;

import com.grid.dynamics.demoprojecthospital.models.Currency;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.time.ZoneId;


/**
 * The main purpose of this (CurrencyServiceApi) class provide up-to-date currency course of needed currency.
 */
@Service
@RequiredArgsConstructor
public class CurrencyServiceApi {
    private final RestTemplate restTemplate;

    @Value("${currency.usd}")
    private String urlUSD;
    @Value("${currency.eur}")
    private String urlEUR;
    @Value("${currency.rub}")
    private String urlRUB;

    /**
     * @return JSON with the same fields that has Class Currency, but with USD currency.
     */
    public Currency[] getUSD() {
        Currency[] forObject = restTemplate.getForObject(urlUSD + currentTime(), Currency[].class);
        return forObject;
    }

    /**
     * @return JSON with the same fields that has Class Currency, but with EUR currency.
     */
    public Currency[] getEUR() {
        Currency[] forObject = restTemplate.getForObject(urlEUR + currentTime(), Currency[].class);
        return forObject;
    }

    /**
     * @return JSON with the same fields that has Class Currency, but with RUB currency.
     */
    public Currency[] getRUB() {
        Currency[] forObject = restTemplate.getForObject(urlRUB + currentTime(), Currency[].class);
        return forObject;
    }

    /**
     * @return result of LocalDate.now, but different format (Example: 20220107 ).
     */
    private String currentTime() {
        return LocalDate.now(ZoneId.of("UTC")).toString().replace("-", "").trim();
    }


}
