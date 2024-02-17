package med.voll.api.domain.appointment.validations;

import jakarta.validation.ValidationException;
import med.voll.api.domain.appointment.CreateScheduleDTO;
import med.voll.api.domain.doctor.IDoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ActiveDoctorValidator implements IAppointmentSchedulingValidator {
    @Autowired
    private IDoctorRepository repository;

    public void valid(CreateScheduleDTO scheduleDTO){
        if (scheduleDTO.idDoctor() == null)
            return;

        Boolean active = repository.findActiveById(scheduleDTO.idDoctor());

        if (!active)
            throw new ValidationException("Médico inativo");
    }
}
