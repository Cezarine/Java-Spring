package med.voll.api.domain.doctor;

import jakarta.persistence.*;
import lombok.*;
import med.voll.api.domain.endereco.Endereco;

@Table(name = "doctors")
@Entity(name = "Doctor")
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Getter
public class Doctor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private String tell;
    private String crm;
    @Enumerated(EnumType.STRING)
    private Especialidade especialidade;
    @Embedded
    private Endereco adress;
    private boolean active;

    public Doctor(CreateDoctorDTO doctor) {
        this.active = true;
        this.name = doctor.name();
        this.crm = doctor.crm();
        this.email = doctor.email();
        this.tell = doctor.tell();
        this.especialidade = doctor.especialidade();
        this.adress = new Endereco(doctor.adress());
    }

    public void UpdateDoctor(UpdateDoctorDTO dto){
        if (dto.name() != null)
            this.name = dto.name();
        if (dto.tell() != null)
            this.tell = dto.tell();
        if (dto.adress() != null)
            this.adress.UpdateAdress(dto.adress());
    }

    public void DeleteLogic() {
        this.active = false;
    }
}
