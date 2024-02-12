package med.voll.api.domain.medico;

import jakarta.persistence.*;
import lombok.*;
import med.voll.api.domain.endereco.Endereco;

@Table(name = "medicos")
@Entity(name = "Medico")
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Getter
public class Medico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String email;
    private String telefone;
    private String crm;
    @Enumerated(EnumType.STRING)
    private Especialidade especialidade;
    @Embedded
    private Endereco endereco;
    private boolean ativo;

    public Medico(CreateDoctorDTO doctor) {
        this.ativo = true;
        this.nome = doctor.nome();
        this.crm = doctor.crm();
        this.email = doctor.email();
        this.telefone = doctor.telefone();
        this.especialidade = doctor.especialidade();
        this.endereco = new Endereco(doctor.endereco());
    }

    public void UpdateDoctor(UpdateDoctorDTO dto){
        if (dto.nome() != null)
            this.nome = dto.nome();
        if (dto.telefone() != null)
            this.telefone = dto.telefone();
        if (dto.endereco() != null)
            this.endereco.UpdateAdress(dto.endereco());
    }

    public void DeleteLogic() {
        this.ativo = false;
    }
}
