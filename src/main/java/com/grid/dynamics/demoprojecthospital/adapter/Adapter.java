package com.grid.dynamics.demoprojecthospital.adapter;

import com.grid.dynamics.demoprojecthospital.models.enums.MicroserviceURLS;
import com.grid.dynamics.demoprojecthospital.models.enums.PdmEndpoints;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@RequiredArgsConstructor
@Component
public class Adapter {
    private final RestTemplate restTemplate;

    public <T, V> ResponseEntity<T> getResponseEntity(MicroserviceURLS serviceResourceUrl, String authorization, PdmEndpoints url, V body, Class<T> returnType, Object... params) {
        String path = String.format(url.getPath(), params);
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", authorization);
        HttpEntity<V> requestEntity = new HttpEntity<>(body, headers);
        return restTemplate.exchange(serviceResourceUrl.getPath() + path, HttpMethod.GET, requestEntity, returnType);
    }

    public <T, V> ResponseEntity<T> getResponseEntity(String serviceResourceUrl, String authorization, String url, V body, Class<T> returnType, Object... params) {
        String path = String.format(url, params);
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", authorization);
        HttpEntity<V> requestEntity = new HttpEntity<>(body, headers);
        return restTemplate.exchange(serviceResourceUrl + path, HttpMethod.GET, requestEntity, returnType);
    }
}