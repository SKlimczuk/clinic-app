package custom.clinic.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "visits")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Visit {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String tittle;

    private LocalDate dateOfVisit;

    private LocalTime timeOfVisit;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
