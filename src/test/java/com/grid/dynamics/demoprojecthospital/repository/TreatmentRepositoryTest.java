package com.grid.dynamics.demoprojecthospital.repository;

import com.grid.dynamics.demoprojecthospital.models.TreatmentEntity;
import com.grid.dynamics.demoprojecthospital.models.enums.Status;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;

@DataJpaTest
class TreatmentRepositoryTest {

    @Autowired
    private TreatmentRepository treatmentRepository = mock(TreatmentRepository.class);

    @AfterEach
    void tearDown() {
        treatmentRepository.deleteAll();
    }

    @Test
    @DisplayName("This test should check correct work 'exists by treatment id' and return boolean if treatment with this id exists")
    void ShouldCheckExistsByIdAndSaveToRepository() {
        LocalDate startDate = LocalDate.now(ZoneId.of("UTC"));
        TreatmentEntity treatment = new TreatmentEntity(Status.PREPARING, "Treat stomach", 569L, 345L, "K-154", 6300.0, "UAH",
                startDate, startDate.plusDays(7L), 7L, 654L, "Patient has complain to sick stomach");
        treatmentRepository.save(treatment);
        boolean resultExists = treatmentRepository.existsById(treatment.getId());
        assertTrue(resultExists);
    }

    @Test
    @DisplayName("(False)This test should check correct work 'exists by treatment id' and return boolean if treatment doesn't exists with this id")
    void ShouldCheckExistsByIdAndDoesntSaveRepository() {
        LocalDate startDate = LocalDate.now(ZoneId.of("UTC"));
        TreatmentEntity treatment = new TreatmentEntity(1L, Status.PREPARING, "Treat stomach", 569L, 345L, "K-154", 6300.0, "UAH",
                startDate, startDate.plusDays(7L), 7L, 654L, "Patient has complain to sick stomach", new HashSet<>(), new HashSet<>());
        boolean resultExists = treatmentRepository.existsById(treatment.getId());
        Assertions.assertThat(resultExists).isFalse();
        assertFalse(resultExists);
    }
}