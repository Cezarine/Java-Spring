package med.voll.api.domain.appointment;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import med.voll.api.domain.doctor.Especialidade;

import java.time.LocalDateTime;

public record CreateScheduleDTO(
        Long idDoctor,

        @NotNull
        Long idPatient,

        Especialidade especialidade,

        @NotNull
        @Future
        @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
        LocalDateTime data) {
}
