package custom.clinic.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Entity
@Table(name = "visits")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Visit {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String tittle;

    private String note;

    private LocalDate dateOfVisit;

    private LocalTime timeOfVisit;

    @ManyToOne
    @JoinColumn(name = "patient_id")
    private Patient patient;

    @ManyToOne
    @JoinColumn(name = "doctor_id")
    private Doctor doctor;
}
