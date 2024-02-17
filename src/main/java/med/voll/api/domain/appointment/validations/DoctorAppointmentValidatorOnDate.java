package med.voll.api.domain.appointment.validations;

import jakarta.validation.ValidationException;
import med.voll.api.domain.appointment.CreateScheduleDTO;
import med.voll.api.domain.appointment.IAppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DoctorAppointmentValidatorOnDate implements IAppointmentSchedulingValidator {
    @Autowired
    private IAppointmentRepository repository;

    public void valid(CreateScheduleDTO scheduleDTO){
        Boolean IsAppointment = repository.existsByDoctorIdAndData(scheduleDTO.idDoctor(), scheduleDTO.data());

        if (IsAppointment)
            throw new ValidationException("Doutor já possui consulta nesse horário");
    }
}
