package custom.clinic.model;

import javax.persistence.*;

@Entity
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

    public Patient() {
    }
}
