package med.voll.api.domain.patient;

public record ReadPatientDTO(Long id, String name, String email, String cpf) {

    public ReadPatientDTO(Patient patient){
        this(patient.getId(), patient.getName(), patient.getEmail(), patient.getCpf());
    }
}
