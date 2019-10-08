package custom.clinic.model;

import javax.persistence.Entity;
import javax.persistence.Table;

public enum Role {
    ADMIN,
    DOCTOR,
    PATIENT,
    ANONYMOUS
}
