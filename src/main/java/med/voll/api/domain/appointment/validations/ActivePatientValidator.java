package med.voll.api.domain.appointment.validations;

import jakarta.validation.ValidationException;
import med.voll.api.domain.appointment.CreateScheduleDTO;
import med.voll.api.domain.patient.IPatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ActivePatientValidator implements IAppointmentSchedulingValidator {

    @Autowired
    private IPatientRepository repository;

    public void valid(CreateScheduleDTO scheduleDTO){
        Boolean active = repository.findActiveById(scheduleDTO.idPatient());

        if (!active)
            throw new ValidationException("Paciente inativo");
    }
}
