package med.voll.api.domain.appointment;

import jakarta.persistence.*;
import lombok.*;
import med.voll.api.domain.doctor.Doctor;
import med.voll.api.domain.patient.Patient;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "appointments")
@Entity(name = "Appointment")
@EqualsAndHashCode(of = "id")
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "doctor_id")
    private Doctor doctor;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "patient_id")
    private Patient patient;

    private LocalDateTime data;

}
