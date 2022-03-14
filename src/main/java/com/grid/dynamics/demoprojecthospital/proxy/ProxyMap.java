package com.grid.dynamics.demoprojecthospital.proxy;

import com.grid.dynamics.demoprojecthospital.models.AppointmentModel;
import com.grid.dynamics.demoprojecthospital.models.Room;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(name = "hospital-service")
public interface ProxyMap {
    @GetMapping("/api/hospitals/")
    List<Room> rooms();
}
