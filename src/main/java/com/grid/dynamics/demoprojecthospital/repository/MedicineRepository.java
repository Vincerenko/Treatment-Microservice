package com.grid.dynamics.demoprojecthospital.repository;

import com.grid.dynamics.demoprojecthospital.models.Medicine;
import com.grid.dynamics.demoprojecthospital.models.enums.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

/**
 * @version 1.0
 * This interface performs role CRUD repository for entity 'Medicine' with type of id 'Long'
 * @autor Alex Sanin
 */
@Repository
@Transactional
public interface MedicineRepository extends JpaRepository<Medicine, Long> {

    boolean existsByOtherId(Long Long);

    @Modifying
    @Query("update medicine e set e.count = :count+e.count where e.otherId = :id")
    void updateMedicineCount(@Param(value = "count") int count,@Param(value = "id") Long id);
}
