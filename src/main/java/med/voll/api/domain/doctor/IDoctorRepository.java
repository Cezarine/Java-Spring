package med.voll.api.domain.doctor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;

public interface IDoctorRepository extends JpaRepository<Doctor, Long> {
    Page<Doctor> findAllByActiveTrue(Pageable pageable);

    @Query("""
            SELECT
              d
            FROM
              Doctor d
            WHERE d.active = true
              AND d.especialidade = :especialidade
              AND d.id NOT IN(
              SELECT
                a.doctor.id
              FROM
                Appointment a
              WHERE a.data = :dateTime)
            ORDER BY RAND()
            LIMIT 1
            """)
    Doctor ChooseFreeRandomDoctorOnDate(Especialidade especialidade, LocalDateTime dateTime);

    @Query("""
            SELECT
                d.active
            FROM
                Doctor d
            WHERE d.id = :id
            """)
    Boolean findActiveById(Long id);
}
