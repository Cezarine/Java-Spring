package med.voll.api.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.domain.appointment.CreateAppointmentDTO;
import med.voll.api.domain.appointment.ReadAppointmentDetailedDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("appointment")
public class AppointmentController {
    @PostMapping
    @Transactional
    public ResponseEntity<ReadAppointmentDetailedDTO> Schedule(@RequestBody @Valid CreateAppointmentDTO appointmentDTO){

        System.out.println(appointmentDTO);

        return ResponseEntity.ok(new ReadAppointmentDetailedDTO(null, null, null,null));

    }
}
