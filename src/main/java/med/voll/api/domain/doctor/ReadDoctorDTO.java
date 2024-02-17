package med.voll.api.domain.doctor;
public record ReadDoctorDTO(Long id, String name, String email, String crm, Especialidade especialidade) {
    public ReadDoctorDTO(Doctor medico){
        this(   medico.getId(),
                medico.getName(),
                medico.getCrm(),
                medico.getEmail(),
                medico.getEspecialidade());
    }
}
