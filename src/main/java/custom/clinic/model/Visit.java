package custom.clinic.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
public class Visit {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String note;

    private LocalDateTime termOfVisit;

    //relacje z lekarzem i pacjentem -> do zastanowienia

    public Visit() {
    }
}
