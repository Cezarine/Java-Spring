package med.voll.api.domain.doctor;

import med.voll.api.domain.appointment.Appointment;
import med.voll.api.domain.endereco.CreateEnderecoDTO;
import med.voll.api.domain.patient.CreatePatientDTO;
import med.voll.api.domain.patient.Patient;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
class IDoctorRepositoryTest {

    @Autowired
    private IDoctorRepository repository;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    @DisplayName("Deveria devolver null quando o único médico cadastrado não está disponível na data")
    void chooseFreeRandomDoctorOnDate_Scenario1() {
        //Given or Arrange
        LocalDateTime nextMondayAt10 = LocalDate.now()
                .with(TemporalAdjusters.next(DayOfWeek.MONDAY))
                .atTime(10, 0);

        Doctor doctor = CadastrarDoctor("Médico", "medico@voll.med", "123456", Especialidade.CARDIOLOGIA);
        Patient patient = CadastrarPatient("Paciente", "paciente@voll.med", "00000000000");
        CadastrarAppointment(doctor, patient, nextMondayAt10);

        //When or Act
        Doctor doctorFree = repository.ChooseFreeRandomDoctorOnDate(Especialidade.CARDIOLOGIA, nextMondayAt10);

        //Then or Assert
        assertThat(doctorFree).isNull();
    }

    @Test
    @DisplayName("Deveria devolver Médico quanto ele estiver disponível na data")
    void chooseFreeRandomDoctorOnDate_Scenario2() {
        //Given or Arrange
        LocalDateTime nextMondayAt10 = LocalDate.now()
                .with(TemporalAdjusters.next(DayOfWeek.MONDAY))
                .atTime(10, 0);

        Doctor doctor = CadastrarDoctor("Médico", "medico@voll.med", "123456", Especialidade.CARDIOLOGIA);

        //When or Act
        Doctor doctorFree = repository.ChooseFreeRandomDoctorOnDate(Especialidade.CARDIOLOGIA, nextMondayAt10);

        //Then or Assert
        assertThat(doctorFree).isEqualTo(doctor);
    }

    private void CadastrarAppointment(Doctor doctor, Patient patient, LocalDateTime data) {
        entityManager.persist(new Appointment(null, doctor, patient, data, null, false));
    }

    private Doctor CadastrarDoctor(String name, String email, String crm, Especialidade especialidade) {
        var doctor = new Doctor(CreateDoctor(name, email, crm, especialidade));
        entityManager.persist(doctor);
        return doctor;
    }

    private Patient CadastrarPatient(String name, String email, String cpf) {
        var patient = new Patient(CreatePatient(name, email, cpf));
        entityManager.persist(patient);
        return patient;
    }

    private CreateDoctorDTO CreateDoctor(String name, String email, String crm, Especialidade especialidade) {
        return new CreateDoctorDTO(
                name,
                email,
                "61999999999",
                crm,
                especialidade,
                CreateEndereco()
        );
    }

    private CreatePatientDTO CreatePatient(String name, String email, String cpf) {
        return new CreatePatientDTO(
                name,
                email,
                "61999999999",
                cpf,
                CreateEndereco()
        );
    }

    private CreateEnderecoDTO CreateEndereco() {
        return new CreateEnderecoDTO(
                "rua xpto",
                "bairro",
                "00000000",
                "Brasilia",
                "DF",
                null,
                null
        );
    }
}