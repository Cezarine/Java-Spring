package med.voll.api.domain.appointment.validations.cancellation;

import med.voll.api.domain.appointment.DeleteAppointmentDTO;

public interface ICancellationSchedulerValidator {
    void valid(DeleteAppointmentDTO appointmentDTO);
}
