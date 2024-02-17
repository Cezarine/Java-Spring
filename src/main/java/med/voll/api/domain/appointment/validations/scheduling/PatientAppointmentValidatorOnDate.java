package med.voll.api.domain.appointment.validations.scheduling;

import jakarta.validation.ValidationException;
import med.voll.api.domain.appointment.CreateScheduleDTO;
import med.voll.api.domain.appointment.IAppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class PatientAppointmentValidatorOnDate implements IAppointmentSchedulingValidator {

    @Autowired
    private IAppointmentRepository repository;
    public void valid(CreateScheduleDTO scheduleDTO){
        LocalDateTime startDate = scheduleDTO.data().withHour(7);
        LocalDateTime endDate = scheduleDTO.data().withHour(18);

        Boolean IsAppointment = repository.existsByPatientIdAndDataBetweenAndCancelledIsFalse(scheduleDTO.idPatient(), startDate, endDate);

        if (IsAppointment)
            throw new ValidationException("Paciente já possui uma consulta hoje");
    }
}
