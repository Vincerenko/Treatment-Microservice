package com.grid.dynamics.demoprojecthospital.repository;

import com.grid.dynamics.demoprojecthospital.models.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {

    Schedule findScheduleByPatientId(Long id);

}
