package med.voll.api.domain.patient;

import med.voll.api.domain.endereco.UpdateEnderecoDTO;

public record UpdatePatientDTO(
        String name,
        String tell,
        UpdateEnderecoDTO adress
) {
}
