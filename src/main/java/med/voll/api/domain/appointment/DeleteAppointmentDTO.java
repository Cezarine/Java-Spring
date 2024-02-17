package med.voll.api.domain.appointment;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DeleteAppointmentDTO(
        @NotNull
        Long idAppointment,

        @NotBlank
        String reason) {
}
