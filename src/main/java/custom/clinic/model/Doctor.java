package custom.clinic.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "doctors")
@Getter
@Setter
public class Doctor {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String name;

    private String surname;

    private String specialization;

    @Column(unique = true)
    private String email;

    private String password;

    @OneToMany(mappedBy = "doctor")
    private List<Visit> visits;

    public Doctor() {
    }
}
