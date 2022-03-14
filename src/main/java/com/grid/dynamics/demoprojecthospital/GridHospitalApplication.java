package com.grid.dynamics.demoprojecthospital;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients("com.grid.dynamics.demoprojecthospital")
public class GridHospitalApplication {

    public static void main(String[] args) {
        SpringApplication.run(GridHospitalApplication.class, args);
    }

}
