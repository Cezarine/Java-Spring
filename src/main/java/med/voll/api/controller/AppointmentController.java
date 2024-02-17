package med.voll.api.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.domain.appointment.AppointmentScheduleService;
import med.voll.api.domain.appointment.CreateScheduleDTO;
import med.voll.api.domain.appointment.ReadScheduleDetailedDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("appointment")
public class AppointmentController {

    @Autowired
    private AppointmentScheduleService service;

    @PostMapping
    @Transactional
    public ResponseEntity<ReadScheduleDetailedDTO> Schedule(@RequestBody @Valid CreateScheduleDTO scheduleDTO){
        ReadScheduleDetailedDTO scheduleDetailedDTO =  service.Schedule(scheduleDTO);
        return ResponseEntity.ok(scheduleDetailedDTO);

    }
}
