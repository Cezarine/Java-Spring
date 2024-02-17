package med.voll.api.domain.appointment.validations.cancellation;

import jakarta.validation.ValidationException;
import med.voll.api.domain.appointment.Appointment;
import med.voll.api.domain.appointment.DeleteAppointmentDTO;
import med.voll.api.domain.appointment.IAppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;

@Component("ValidatorEarlySchedulingCancellation")
public class AdvanceSchedulingValidator implements ICancellationSchedulerValidator{

    @Autowired
    private IAppointmentRepository repository;
    public void valid(DeleteAppointmentDTO deleteAppointmentDTO){
        Appointment appointment = repository.getReferenceById(deleteAppointmentDTO.idAppointment());
        LocalDateTime now = LocalDateTime.now();
        long differenceInHours = Duration.between(now, appointment.getData()).toHours();

        if (differenceInHours < 24)
            throw new ValidationException("Consulta somente pode ser cancelada com antecedência miníma de 24 horas");


    }
}
