package custom.clinic.model;

import javax.persistence.Entity;

@Entity
public enum Role {
    ADMIN,
    PATIENT,
    DOCTOR
}
