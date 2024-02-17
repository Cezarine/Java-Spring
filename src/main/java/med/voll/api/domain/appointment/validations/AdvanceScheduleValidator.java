package med.voll.api.domain.appointment.validations;

import jakarta.validation.ValidationException;
import med.voll.api.domain.appointment.CreateScheduleDTO;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;

@Component
public class AdvanceScheduleValidator implements IAppointmentSchedulingValidator {
    public void valid(CreateScheduleDTO scheduleDTO){
        LocalDateTime appointmentDate = scheduleDTO.data();

        LocalDateTime now = LocalDateTime.now();


        var differenceInMinutes = Duration.between(now, appointmentDate).toMinutes();

        if (differenceInMinutes < 30)
            throw new ValidationException("Consulta deve ser agendada com pelo menos 30 minutos de antecedência");
    }

}
