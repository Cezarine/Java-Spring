package med.voll.api.domain.appointment;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;

public interface IAppointmentRepository extends JpaRepository<Appointment, Long> {

    Boolean existsByPatientIdAndDataBetween(Long idPatient, LocalDateTime startDate, LocalDateTime endDate);

    Boolean existsByDoctorIdAndData(Long idDoctor, LocalDateTime data);
}
