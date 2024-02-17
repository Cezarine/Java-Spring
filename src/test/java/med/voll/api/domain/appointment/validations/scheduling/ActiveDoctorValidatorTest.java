package med.voll.api.domain.appointment.validations.scheduling;

import jakarta.validation.ValidationException;
import med.voll.api.domain.appointment.Appointment;
import med.voll.api.domain.appointment.CreateScheduleDTO;
import med.voll.api.domain.doctor.CreateDoctorDTO;
import med.voll.api.domain.doctor.Doctor;
import med.voll.api.domain.doctor.Especialidade;
import med.voll.api.domain.doctor.IDoctorRepository;
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

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
class ActiveDoctorValidatorTest {
    @Autowired
    private IDoctorRepository repository;

    @Autowired
    private TestEntityManager entityManager;
    @Test
    @DisplayName("Deve retornar true quando envia um médico, pois não pode existir médicos inativos")
    void valid_Scenario1() {

        Doctor doctor = CadastrarDoctor("Médico", "medico@voll.med", "123456", Especialidade.CARDIOLOGIA);
        Patient patient = CadastrarPatient("Paciente", "paciente@voll.med", "00000000000");
        Appointment appointment = CadastrarAppointment(doctor, patient, LocalDateTime.now());

        CreateScheduleDTO scheduleDTO = new CreateScheduleDTO(
                appointment.getDoctor().getId(),
                appointment.getPatient().getId(),
                appointment.getDoctor().getEspecialidade(),
                appointment.getData()
        );


        Boolean active = scheduleDTO.idDoctor() == null || repository.findActiveById(scheduleDTO.idDoctor());

        assertThat(active).isTrue();
    }

    @Test
    @DisplayName("Deve retornar true quando não envia um médico, pois não pode existir médicos inativos")
    void valid_Scenario2() {

        Doctor doctor = CadastrarDoctor("Médico", "medico@voll.med", "123456", Especialidade.CARDIOLOGIA);
        Patient patient = CadastrarPatient("Paciente", "paciente@voll.med", "00000000000");
        Appointment appointment = CadastrarAppointment(doctor, patient, LocalDateTime.now());

        CreateScheduleDTO scheduleDTO = new CreateScheduleDTO(
                null,
                appointment.getPatient().getId(),
                appointment.getDoctor().getEspecialidade(),
                appointment.getData()
        );


        Boolean active = scheduleDTO.idDoctor() == null || repository.findActiveById(scheduleDTO.idDoctor());

        assertThat(active).isTrue();
    }


    @Test
    @DisplayName("Deve retornar false, pois não pode existir médicos inativos")
    void valid_Scenario3() {

        Doctor doctor = CadastrarDoctor("Médico", "medico@voll.med", "123456", Especialidade.CARDIOLOGIA);
        doctor.DeleteLogic();
        Patient patient = CadastrarPatient("Paciente", "paciente@voll.med", "00000000000");
        Appointment appointment = CadastrarAppointment(doctor, patient, LocalDateTime.now());

        CreateScheduleDTO scheduleDTO = new CreateScheduleDTO(
                appointment.getDoctor().getId(),
                appointment.getPatient().getId(),
                appointment.getDoctor().getEspecialidade(),
                appointment.getData()
        );

        Boolean active = repository.findActiveById(scheduleDTO.idDoctor());

        assertThat(active).isFalse();
    }

    private Appointment CadastrarAppointment(Doctor doctor, Patient patient, LocalDateTime data) {
        var appointment = new Appointment(null, doctor, patient, data, null, false);
        entityManager.persist(appointment);
        return appointment;
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