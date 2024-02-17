package med.voll.api.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.domain.patient.IPatientRepository;
import med.voll.api.domain.patient.Patient;
import med.voll.api.domain.patient.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("patients")
@SecurityRequirement(name = "bearer-key")
public class PatientController {

    @Autowired
    private IPatientRepository repository;
    @PostMapping
    @Transactional
    public ResponseEntity<Object> Cadastrar(@RequestBody @Valid CreatePatientDTO patientDTO, UriComponentsBuilder uriComponentsBuilder){
        Patient patient = new Patient(patientDTO);
        repository.save(patient);

        URI uri = uriComponentsBuilder.path("/patients/{id}").buildAndExpand(patient.getId()).toUri();

        return ResponseEntity.created(uri).body(new ReadPatientDetailedDTO(patient));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReadPatientDetailedDTO> ListarPorID(@PathVariable Long id){
        Patient patient = repository.getReferenceById(id);

        return ResponseEntity.ok(new ReadPatientDetailedDTO(patient));
    }

    @GetMapping
    public ResponseEntity<Page<ReadPatientDTO>> Listar(@PageableDefault(sort={"name"}, direction = Sort.Direction.ASC, size = 10) Pageable pageable){
         Page<ReadPatientDTO> patients = repository.findAllByActiveTrue(pageable).map(ReadPatientDTO::new);

        return ResponseEntity.ok(patients);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<Object> Atualizar(@PathVariable Long id, @RequestBody @Valid UpdatePatientDTO patientDTO){
        Patient patient = repository.getReferenceById(id);

        patient.UpdatePatient(patientDTO);

        return ResponseEntity.ok(new ReadPatientDetailedDTO(patient));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Object> Deletar(@PathVariable Long id){
        Patient patient = repository.getReferenceById(id);

        patient.DeleteLogic();

        return ResponseEntity.noContent().build();
    }
}
