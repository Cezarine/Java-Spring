package med.voll.api.domain.endereco;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record CreateEnderecoDTO(
        @NotBlank
        String adress,

        @NotBlank
        String neighborhood,

        @NotBlank
        @Pattern(regexp = "\\d{8}")
        String zip_code,

        @NotBlank
        String city,

        @NotBlank
        String uf,

        String number,

        String complement) {
}
