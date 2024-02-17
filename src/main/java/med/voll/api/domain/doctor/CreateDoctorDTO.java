package med.voll.api.domain.doctor;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import med.voll.api.domain.endereco.CreateEnderecoDTO;

public record CreateDoctorDTO(

        @NotBlank
        String name,

        @NotBlank
        @Email
        String email,

        @NotBlank
        String tell,

        @NotBlank
        @Pattern(regexp = "\\d{4,6}")
        String crm,

        @NotNull
        Especialidade especialidade,

        @NotNull
        @Valid
        CreateEnderecoDTO adress) {
}
