package custom.clinic.model;

import javax.persistence.*;

@Entity
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

    public Doctor() {
    }
}
