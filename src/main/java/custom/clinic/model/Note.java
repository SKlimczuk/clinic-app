package custom.clinic.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "notes")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Note implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String note;

    @ManyToOne
    @JoinColumn(name = "visit_id")
    @JsonBackReference
    private Visit visit;

    @Override
    public String toString() {
        return "note=" + note;
    }
}
