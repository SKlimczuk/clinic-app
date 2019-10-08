package custom.clinic.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "doctors")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Doctor {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String name;

    private String surname;

    private String specialization;

    @Column(unique = true)
    private String email;

    private String phone;

    private String password;

    @OneToMany(mappedBy = "doctor")
    private List<Visit> visits;
}
