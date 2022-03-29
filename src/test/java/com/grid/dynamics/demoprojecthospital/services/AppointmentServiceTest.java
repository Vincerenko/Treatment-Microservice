package com.grid.dynamics.demoprojecthospital.services;

import com.grid.dynamics.demoprojecthospital.models.Appointment;
import com.grid.dynamics.demoprojecthospital.models.TreatmentEntity;
import com.grid.dynamics.demoprojecthospital.repository.AppointmentRepository;
import com.grid.dynamics.demoprojecthospital.repository.TreatmentRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;

@ExtendWith(MockitoExtension.class)
class AppointmentServiceTest {
    @Mock
    private AppointmentRepository appointmentRepository;
    @Mock
    private TreatmentRepository treatmentRepository;
    @InjectMocks
    private AppointmentService appointmentService;


    @Test
    void ShouldSaveAppointment() {
        Appointment appointment = new Appointment();
        appointment.setPrice(100.0);
        appointment.setAmount(2);
        Mockito.when(appointmentRepository.save(appointment)).thenReturn(appointment);
        appointmentService.saveAppointment(appointment, 1L);
    }

    @Test
    void ShouldSaveAppointmentSecondTest() {
        TreatmentEntity treatmentEntity = new TreatmentEntity();
        Appointment appointment2 = new Appointment(1L,"APP",200.0,3, LocalDateTime.now(),new TreatmentEntity());
        treatmentEntity.setId(45L);
        appointment2.setTreatment(treatmentEntity);
        appointment2.setTreatment(new TreatmentEntity());
        Mockito.doNothing().when(treatmentRepository).updateTreatmentPriceById(appointment2.getPrice() * appointment2.getAmount(),1L);
        Mockito.when(appointmentRepository.save(appointment2)).thenReturn(appointment2);
        appointmentService.saveAppointment(appointment2, 1L);
    }

    @Test
    void ShouldSaveAppointmentThirdTest() {
        Appointment appointment = new Appointment();
        appointment.setId(56L);
        appointment.setAmount(3);
        appointment.setName("Test");
        appointment.setMeetDate(LocalDateTime.now());
        appointment.setPrice(200.0);
        Mockito.when(appointmentRepository.save(appointment)).thenReturn(appointment);
        appointmentService.saveAppointment(appointment, 1L);
        Mockito.verify(appointmentRepository, Mockito.times(1)).save(appointment);
    }


}