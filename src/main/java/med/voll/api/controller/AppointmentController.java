package med.voll.api.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.domain.appointment.AppointmentScheduleService;
import med.voll.api.domain.appointment.CreateScheduleDTO;
import med.voll.api.domain.appointment.DeleteAppointmentDTO;
import med.voll.api.domain.appointment.ReadScheduleDetailedDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("appointment")
@SecurityRequirement(name = "bearer-key")
public class AppointmentController {

    @Autowired
    private AppointmentScheduleService service;

    @PostMapping
    @Transactional
    public ResponseEntity<ReadScheduleDetailedDTO> Schedule(@RequestBody @Valid CreateScheduleDTO scheduleDTO){
        ReadScheduleDetailedDTO scheduleDetailedDTO =  service.Schedule(scheduleDTO);
        return ResponseEntity.ok(scheduleDetailedDTO);

    }

    @DeleteMapping
    @Transactional
    public ResponseEntity<Object> Delete(@RequestBody @Valid DeleteAppointmentDTO appointmentDTO){
        service.Delete(appointmentDTO);

        return ResponseEntity.noContent().build();
    }
}
