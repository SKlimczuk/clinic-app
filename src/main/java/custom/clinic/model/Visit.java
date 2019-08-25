package custom.clinic.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Entity
@Table(name = "visits")
@Getter
@Setter
public class Visit {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String note;

    private LocalDate dateOfVisit;

    private LocalTime timeOfVisit;

    @ManyToOne
    @JoinColumn(name = "patient_id")
    private Patient patient;

    @ManyToOne
    @JoinColumn(name = "doctor_id")
    private Doctor doctor;

    public Visit() {
    }
}
