package custom.clinic.model;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "doctors")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Doctor implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String specialization;

    @OneToOne(mappedBy = "doctor")
    private User user;

    @OneToMany(mappedBy = "doctor")
    private List<Visit> visits;
}
