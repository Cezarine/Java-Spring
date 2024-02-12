package med.voll.api.domain.medico;

import med.voll.api.domain.endereco.Endereco;

public record ReadDoctorDetailedDTO(Long id, String nome, String email, String crm, Especialidade especialidade, String telefone, Endereco endereco) {

    public ReadDoctorDetailedDTO(Medico doctor){
        this(   doctor.getId(),
                doctor.getNome(),
                doctor.getCrm(),
                doctor.getEmail(),
                doctor.getEspecialidade(),
                doctor.getTelefone(),
                doctor.getEndereco());
    }
}
