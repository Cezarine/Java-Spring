package med.voll.api.domain.appointment.validations.scheduling;

import med.voll.api.domain.appointment.CreateScheduleDTO;

public interface IAppointmentSchedulingValidator {
    void valid(CreateScheduleDTO scheduleDTO);
}
