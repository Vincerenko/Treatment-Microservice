package com.grid.dynamics.demoprojecthospital.controller;

import com.grid.dynamics.demoprojecthospital.models.AppointmentModel;
import com.grid.dynamics.demoprojecthospital.models.Room;
import com.grid.dynamics.demoprojecthospital.proxy.ProxyAppointment;
import com.grid.dynamics.demoprojecthospital.proxy.ProxyMap;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class AppointmentModelController {
    private final ProxyAppointment proxyAppointment;
    private final ProxyMap proxyMap;

    @GetMapping("/appointments")
    public List<AppointmentModel> appointmentModels (){
        return proxyAppointment.appointmentsDoctor();
    }

    @GetMapping("/appointmentsPatient")
    public List<AppointmentModel> appointmentModelsPatient (){
        return proxyAppointment.appointmentsPatient();
    }

    @GetMapping("/rooms")
    public List<Room> rooms (){
        return proxyMap.rooms();
    }

}
