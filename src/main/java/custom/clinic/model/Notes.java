package custom.clinic.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "notes")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Notes {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String note;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
