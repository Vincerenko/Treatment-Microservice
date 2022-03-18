package com.grid.dynamics.demoprojecthospital.adapter;

import com.grid.dynamics.demoprojecthospital.models.enums.PdmURL;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@RequiredArgsConstructor
@Component
public class PdmAdapter {

    private final static String serviceResourceUrl = "https://pdm-service.herokuapp.com";
    private final RestTemplate restTemplate;

    public  <T, V> ResponseEntity<T> getResponseEntity(String authorization, PdmURL url, V body, Class<T> returnType, Object... params) {
        String path = String.format(url.getPath(), params);
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", authorization);

        HttpEntity<V> requestEntity = new HttpEntity<>(body, headers);

        return restTemplate.exchange(serviceResourceUrl + path, HttpMethod.GET, requestEntity, returnType);
    }
}