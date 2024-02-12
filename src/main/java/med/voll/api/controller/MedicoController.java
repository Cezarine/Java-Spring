package med.voll.api.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.domain.medico.IMedicoRepository;
import med.voll.api.domain.medico.Medico;
import med.voll.api.domain.medico.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("medicos")
public class MedicoController {

    @Autowired
    private IMedicoRepository repository;
    @PostMapping
    @Transactional
    public ResponseEntity<Object> Cadastrar(@RequestBody @Valid CreateDoctorDTO doctorDTO, UriComponentsBuilder uriBuilder){
        Medico doctor = new Medico(doctorDTO);
        repository.save(doctor);

        URI uri = uriBuilder.path("/medicos/{id}").buildAndExpand(doctor.getId()).toUri();

        return ResponseEntity.created(uri).body(new ReadDoctorDetailedDTO(doctor));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReadDoctorDetailedDTO> ListarPorID(@PathVariable Long id) {
        Medico doctor = repository.getReferenceById(id);

        return ResponseEntity.ok(new ReadDoctorDetailedDTO(doctor));
    }

    @GetMapping
    public ResponseEntity<Page<ReadDoctorDTO>> Listar(@PageableDefault(size = 10, sort = {"nome"}) Pageable pageable){
        Page<ReadDoctorDTO> doctors =  repository.findAllByAtivoTrue(pageable).map(ReadDoctorDTO::new);

        return ResponseEntity.ok(doctors);
    }

    @PutMapping
    @Transactional
    public ResponseEntity<Object> Alterar(@RequestBody @Valid UpdateDoctorDTO doctorDTO){
        Medico doctor = repository.getReferenceById(doctorDTO.id());

        doctor.UpdateDoctor(doctorDTO);

        return ResponseEntity.ok(new ReadDoctorDetailedDTO(doctor));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Object> Deletar(@PathVariable Long id){
        //repository.deleteById(id);

        Medico doctor = repository.getReferenceById(id);
        doctor.DeleteLogic();

        return ResponseEntity.noContent().build();
    }
}
