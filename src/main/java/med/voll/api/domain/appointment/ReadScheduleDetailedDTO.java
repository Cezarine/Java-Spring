package med.voll.api.domain.appointment;

import java.time.LocalDateTime;

public record ReadScheduleDetailedDTO(Long id, Long idDoctor, Long idPatient, LocalDateTime data) {
    public ReadScheduleDetailedDTO(Appointment schedule) {
        this(schedule.getId(), schedule.getDoctor().getId(), schedule.getPatient().getId(), schedule.getData());
    }
}
