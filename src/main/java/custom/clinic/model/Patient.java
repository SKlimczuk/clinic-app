package custom.clinic.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "patients")
@Getter
@Setter
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String name;

    private String surname;

    @Column(unique = true)
    private String pesel;

    @Column(unique = true)
    private String email;

    private String password;

    private String phone;

    @OneToMany(mappedBy = "patient")
    private List<Visit> visits;

    public Patient() {
    }
}
