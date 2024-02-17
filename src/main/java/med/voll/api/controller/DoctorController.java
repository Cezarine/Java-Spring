package med.voll.api.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.domain.doctor.IDoctorRepository;
import med.voll.api.domain.doctor.Doctor;
import med.voll.api.domain.doctor.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("doctors")
@SecurityRequirement(name = "bearer-key")
public class DoctorController {

    @Autowired
    private IDoctorRepository repository;
    @PostMapping
    @Transactional
    public ResponseEntity<Object> Create(@RequestBody @Valid CreateDoctorDTO doctorDTO, UriComponentsBuilder uriBuilder){
        Doctor doctor = new Doctor(doctorDTO);
        repository.save(doctor);

        URI uri = uriBuilder.path("/doctors/{id}").buildAndExpand(doctor.getId()).toUri();

        return ResponseEntity.created(uri).body(new ReadDoctorDetailedDTO(doctor));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReadDoctorDetailedDTO> FindByID(@PathVariable Long id) {
        Doctor doctor = repository.getReferenceById(id);

        return ResponseEntity.ok(new ReadDoctorDetailedDTO(doctor));
    }

    @GetMapping
    public ResponseEntity<Page<ReadDoctorDTO>> FindAll(@PageableDefault(size = 10, sort = {"name"}) Pageable pageable){
        Page<ReadDoctorDTO> doctors =  repository.findAllByActiveTrue(pageable).map(ReadDoctorDTO::new);

        return ResponseEntity.ok(doctors);
    }

    @PutMapping
    @Transactional
    public ResponseEntity<Object> Alterar(@RequestBody @Valid UpdateDoctorDTO doctorDTO){
        Doctor doctor = repository.getReferenceById(doctorDTO.id());

        doctor.UpdateDoctor(doctorDTO);

        return ResponseEntity.ok(new ReadDoctorDetailedDTO(doctor));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Object> Deletar(@PathVariable Long id){
        //repository.deleteById(id);

        Doctor doctor = repository.getReferenceById(id);
        doctor.DeleteLogic();

        return ResponseEntity.noContent().build();
    }
}
