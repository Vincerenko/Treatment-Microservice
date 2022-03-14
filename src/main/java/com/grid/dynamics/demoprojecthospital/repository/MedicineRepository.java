package com.grid.dynamics.demoprojecthospital.repository;

import com.grid.dynamics.demoprojecthospital.models.Medicine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @version 1.0
 * This interface performs role CRUD repository for entity 'Medicine' with type of id 'Long'
 * @autor Alex Sanin
 */
@Repository
public interface MedicineRepository extends JpaRepository<Medicine, Long> {
}
