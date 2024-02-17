package med.voll.api.domain.doctor;

import jakarta.validation.constraints.NotNull;
import med.voll.api.domain.endereco.UpdateEnderecoDTO;

public record UpdateDoctorDTO(
        @NotNull
        Long id,
        String name,
        String tell,
        UpdateEnderecoDTO adress) {
}
