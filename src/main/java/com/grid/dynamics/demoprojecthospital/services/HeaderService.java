package com.grid.dynamics.demoprojecthospital.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

@Service
@RequiredArgsConstructor
public class HeaderService {
    private final HttpServletRequest request;

    /**
     * @return string of authorization token from header
     */
    public String getToken() {
        return request.getHeader("Authorization");
    }

}