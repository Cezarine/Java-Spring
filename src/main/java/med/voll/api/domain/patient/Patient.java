package med.voll.api.domain.patient;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.voll.api.domain.endereco.Endereco;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "patients")
@Entity(name = "Patient")
@EqualsAndHashCode(of = "id")
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private String tell;
    private String cpf;
    @Embedded
    private Endereco adress;
    private boolean active;

    public Patient(CreatePatientDTO patient) {
        this.active = true;
        this.adress = new Endereco(patient.adress());
        this.email = patient.email();
        this.cpf = patient.cpf();
        this.tell = patient.tell();
        this.name = patient.name();
    }

    public void UpdatePatient(UpdatePatientDTO patientDTO) {
        if (patientDTO.name() != null)
            this.name = patientDTO.name();

        if (patientDTO.tell() != null)
            this.tell = patientDTO.tell();

        if (patientDTO.adress() != null)
            this.adress.UpdateAdress(patientDTO.adress());
    }

    public void DeleteLogic() {
        this.active = false;
    }
}
