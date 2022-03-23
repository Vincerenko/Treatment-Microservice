package com.grid.dynamics.demoprojecthospital.services;

import com.grid.dynamics.demoprojecthospital.models.Schedule;
import com.grid.dynamics.demoprojecthospital.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
@RequiredArgsConstructor
public class ScheduleService {
    private final ScheduleRepository scheduleRepository;
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");


    public void createNewSchedule(Schedule schedule) {
        schedule.getReceptionMedicines().forEach(n -> n.setSchedule(schedule));
        scheduleRepository.save(schedule);
    }

    public Schedule getScheduleByPatientId(Long id) {
        return scheduleRepository.findScheduleByPatientId(id);
    }

    public void deleteSchedule(Long scheduleId){
        scheduleRepository.deleteById(scheduleId);
    }
}
