package com.grid.dynamics.demoprojecthospital.repository;

import com.grid.dynamics.demoprojecthospital.models.TreatmentEntity;
import com.grid.dynamics.demoprojecthospital.models.enums.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;

/**
 * @version 1.0
 * This interface performs role CRUD repository for entity 'TreatmentEntity' with type of id 'Long'
 * @autor Alex Sanin
 */

@Repository
@Transactional
public interface TreatmentRepository extends JpaRepository<TreatmentEntity, Long> {

    /**
     * @param patientId - this param receive id of patient
     * @return return list of treatments that match treatments.patient_id == patientId (param)
     */
    List<TreatmentEntity> findAllByPatientId(Long patientId);

    /**
     * @param treatmentId - this param receive id of 'TreatmentEntity'
     * @param patientId   - - this param receive id of patient
     * @return return one treatment by when id match (treatments.id == treatments.patient_id)
     */
    TreatmentEntity findTreatmentEntityByIdAndPatientId(Long treatmentId, Long patientId);

    /**
     * @param treatmentId - this param receive id of 'TreatmentEntity'
     * @return return type boolean. If treatment exists with id - return 'true', else 'false'
     */
    @Override
    boolean existsById(Long treatmentId);

    /**
     * @param status      write status that you want to change this field
     * @param treatmentId write field which will be changed status
     */

    @Modifying
    @Query("update TreatmentEntity e set e.status = :status where e.id = :id")
    void updateTreatmentStatusById(@Param(value = "status") Status status, @Param(value = "id") Long treatmentId);

    List<TreatmentEntity> findAllByStartDateGreaterThanEqualAndEndDateLessThanEqualAndPatientIdIs(LocalDate beforeDate, LocalDate afterDate, Long patientId);

    @Modifying
    @Query("update TreatmentEntity e set e.price = :price+e.price where e.id = :id")
    void updateTreatmentPriceById(@Param(value = "price") double price, @Param(value = "id") Long treatmentId);


}
