package med.voll.api.domain.patient;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import med.voll.api.domain.endereco.CreateEnderecoDTO;
import org.hibernate.validator.constraints.br.CPF;

public record CreatePatientDTO(
        @NotBlank
        String name,

        @NotBlank
        @Email
        String email,

        @NotBlank
        @CPF
        String cpf,

        String tell,

        @NotNull
        @Valid
        CreateEnderecoDTO adress){
}
