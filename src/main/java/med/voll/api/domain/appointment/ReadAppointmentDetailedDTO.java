package med.voll.api.domain.appointment;

import java.time.LocalDateTime;

public record ReadAppointmentDetailedDTO(Long id, Long idMedico, Long idPatient, LocalDateTime dateTime) {
}
