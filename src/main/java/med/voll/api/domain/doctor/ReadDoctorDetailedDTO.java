package med.voll.api.domain.doctor;

import med.voll.api.domain.endereco.Endereco;

public record ReadDoctorDetailedDTO(Long id, String name, String email, String crm, Especialidade especialidade, String tell, Endereco adress) {

    public ReadDoctorDetailedDTO(Doctor doctor){
        this(   doctor.getId(),
                doctor.getName(),
                doctor.getCrm(),
                doctor.getEmail(),
                doctor.getEspecialidade(),
                doctor.getTell(),
                doctor.getAdress());
    }
}
