package med.voll.api.domain.medico;

import jakarta.validation.constraints.NotNull;
import med.voll.api.domain.endereco.UpdateEnderecoDTO;

public record UpdateDoctorDTO(
        @NotNull
        Long id,
        String nome,
        String telefone,
        UpdateEnderecoDTO endereco) {
}
