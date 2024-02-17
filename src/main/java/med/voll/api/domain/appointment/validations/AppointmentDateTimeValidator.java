package med.voll.api.domain.appointment.validations;

import jakarta.validation.ValidationException;
import med.voll.api.domain.appointment.CreateScheduleDTO;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;
import java.time.LocalDateTime;

@Component
public class AppointmentDateTimeValidator implements IAppointmentSchedulingValidator {
    public void valid(CreateScheduleDTO scheduleDTO) {
        LocalDateTime appointmentDate = scheduleDTO.data();

        Boolean sunday = appointmentDate.getDayOfWeek().equals(DayOfWeek.SUNDAY);
        Boolean clinicOpeningOrClosingTimes = appointmentDate.getHour() < 7 || appointmentDate.getHour() > 18;

        if (sunday || clinicOpeningOrClosingTimes)
            throw new ValidationException("Consulta fora do horário de funcionamento da clínica");

    }
}
