package com.grid.dynamics.demoprojecthospital.services;

import com.grid.dynamics.demoprojecthospital.models.Schedule;
import com.grid.dynamics.demoprojecthospital.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ScheduleService {
    private final ScheduleRepository scheduleRepository;


    public void createNewSchedule(Schedule schedule) {
        schedule.getReceptionMedicines().forEach(n -> n.setSchedule(schedule));
        scheduleRepository.save(schedule);
    }

    public Schedule getScheduleByPatientId(Long id) {
        return scheduleRepository.findScheduleByPatientId(id);
    }
}
