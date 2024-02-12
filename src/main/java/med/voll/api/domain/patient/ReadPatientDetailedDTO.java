package med.voll.api.domain.patient;

import med.voll.api.domain.endereco.Endereco;

public record ReadPatientDetailedDTO(Long id, String name, String email, String tell, String cpf, Endereco adress) {
    public ReadPatientDetailedDTO (Patient patient){
        this(
                patient.getId(),
                patient.getName(),
                patient.getEmail(),
                patient.getTell(),
                patient.getCpf(),
                patient.getAdress()
        );
    }
}
