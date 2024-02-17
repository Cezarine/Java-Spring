package med.voll.api.domain.endereco;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Endereco {
    private String adress;
    private String neighborhood;
    private String zip_code;
    private String city;
    private String uf;
    private String number;
    private String complement;

    public Endereco(CreateEnderecoDTO enderecoDTO) {
        this.neighborhood = enderecoDTO.neighborhood();
        this.zip_code = enderecoDTO.zip_code();
        this.uf = enderecoDTO.uf();
        this.city = enderecoDTO.city();
        this.number = enderecoDTO.number();
        this.complement = enderecoDTO.complement();
        this.adress = enderecoDTO.adress();
    }

    public void UpdateAdress(UpdateEnderecoDTO dto) {
        if (dto.adress() != null)
            this.adress = dto.adress();

        if (dto.zip_code() != null)
            this.zip_code = dto.zip_code();

        if (dto.neighborhood() != null)
            this.neighborhood = dto.neighborhood();

        if (dto.uf() != null)
            this.uf = dto.uf();

        if (dto.city() != null)
            this.city = dto.city();

        if (dto.number() != null)
            this.number = dto.number();

        if (dto.complement() != null)
            this.complement = dto.complement();
    }
}
