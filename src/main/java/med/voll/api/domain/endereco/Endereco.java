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
    private String logradouro;
    private String bairro;
    private String cep;
    private String cidade;
    private String uf;
    private String numero;
    private String complemento;

    public Endereco(CreateEnderecoDTO enderecoDTO) {
        this.bairro = enderecoDTO.bairro();
        this.cep = enderecoDTO.cep();
        this.uf = enderecoDTO.uf();
        this.cidade = enderecoDTO.cidade();
        this.numero = enderecoDTO.numero();
        this.complemento = enderecoDTO.complemento();
        this.logradouro = enderecoDTO.logradouro();
    }

    public void UpdateAdress(UpdateEnderecoDTO dto) {
        if (dto.logradouro() != null)
            this.logradouro = dto.logradouro();

        if (dto.cep() != null)
            this.cep = dto.cep();

        if (dto.bairro() != null)
            this.bairro = dto.bairro();

        if (dto.uf() != null)
            this.uf = dto.uf();

        if (dto.cidade() != null)
            this.cidade = dto.cidade();

        if (dto.numero() != null)
            this.numero = dto.numero();

        if (dto.complemento() != null)
            this.complemento = dto.complemento();
    }
}
