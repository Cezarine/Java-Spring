package med.voll.api.domain.appointment;

import jakarta.validation.ValidationException;
import med.voll.api.domain.appointment.validations.IAppointmentSchedulingValidator;
import med.voll.api.domain.doctor.IDoctorRepository;
import med.voll.api.domain.doctor.Doctor;
import med.voll.api.domain.patient.IPatientRepository;
import med.voll.api.domain.patient.Patient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppointmentScheduleService {

    @Autowired
    private IAppointmentRepository repository;

    @Autowired
    private IDoctorRepository doctorRepository;

    @Autowired
    private IPatientRepository patientRepository;

    @Autowired
    private List<IAppointmentSchedulingValidator> validators;

    public ReadScheduleDetailedDTO Schedule(CreateScheduleDTO scheduleDTO){
        if (scheduleDTO.idDoctor() != null && !doctorRepository.existsById(scheduleDTO.idDoctor())) {
            throw new ValidationException("Id do médico informado não existe");
        }

        if (!patientRepository.existsById(scheduleDTO.idPatient())){
            throw new ValidationException("Id do paciente não existe");
        }

        //Como a lista é feita da interface e existem diversas classes que implementão essa interface, quando faço esse
        //ForEach, o Spring executa o método VALID de todas as classe.
        validators.forEach(v -> v.valid(scheduleDTO));

        Patient patient = patientRepository.getReferenceById(scheduleDTO.idPatient());
        Doctor doctor =  ChooseDoctor(scheduleDTO);
        if (doctor == null)
            throw new ValidationException("Nenhum médico livre nesta data");

        Appointment schedule = new Appointment(null, doctor, patient, scheduleDTO.data());

        repository.save(schedule);

        return new ReadScheduleDetailedDTO(schedule);
    }

    private Doctor ChooseDoctor(CreateScheduleDTO scheduleDTO) {

        if (scheduleDTO.idDoctor() != null)
            return doctorRepository.getReferenceById(scheduleDTO.idDoctor());

        if (scheduleDTO.especialidade() == null)
            throw new ValidationException("Especialidade é obrigatória quando médico não for escolhido");

        return doctorRepository.ChooseFreeRandomDoctorOnDate(scheduleDTO.especialidade(), scheduleDTO.data());
    }

}
