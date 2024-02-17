package med.voll.api.domain.appointment.validations;

import med.voll.api.domain.appointment.CreateScheduleDTO;

public interface IAppointmentSchedulingValidator {
    void valid(CreateScheduleDTO scheduleDTO);
}
