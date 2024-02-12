package med.voll.api.domain.medico;
public record ReadDoctorDTO(Long id, String nome, String email, String crm, Especialidade especialidade) {
    public ReadDoctorDTO(Medico medico){
        this(   medico.getId(),
                medico.getNome(),
                medico.getCrm(),
                medico.getEmail(),
                medico.getEspecialidade());
    }
}
