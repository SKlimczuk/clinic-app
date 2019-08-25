package custom.clinic.model;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "roles")
public enum Role {
    ADMIN,
    DOCTOR,
    PATIENT
}
