package com.grid.dynamics.demoprojecthospital.proxy;

import com.grid.dynamics.demoprojecthospital.models.AppointmentModel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(name = "appointment-service")
public interface ProxyAppointment {
    @GetMapping("/calendar/doctor?doctorId=1")
    List<AppointmentModel> appointmentsDoctor();

    @GetMapping("/calendar/patient?patientId=1")
    List<AppointmentModel> appointmentsPatient();
}
