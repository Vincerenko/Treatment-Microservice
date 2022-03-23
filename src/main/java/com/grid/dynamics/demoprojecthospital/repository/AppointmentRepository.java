package com.grid.dynamics.demoprojecthospital.repository;

import com.grid.dynamics.demoprojecthospital.models.Appointment;
import feign.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.time.LocalDateTime;

/**
 * @version 1.0
 * This interface performs role CRUD repository for entity 'Appointment' with type of id 'Long'
 * @autor Alex Sanin
 */
@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

    @Modifying
    @Query(value = "insert into Appointment (id,count,meet_date,name,price,treatment_id) VALUES (:id,:count,:meet_date,:name,:price,:treatment_id)", nativeQuery = true)
    @Transactional
    void saveApp(@Param("id") Long id, @Param("count") int count, @Param("meet_date") LocalDateTime meet_date,
                 @Param("name") String name,
                 @Param("price") double price, @Param("treatment_id") Long treatment_id);

}
