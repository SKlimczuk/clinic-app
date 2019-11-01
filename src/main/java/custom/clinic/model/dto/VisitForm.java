package custom.clinic.model.dto;

import custom.clinic.model.Doctor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;


@NoArgsConstructor
@Getter
@Setter
public class VisitForm {

    private String title;

    private LocalDate dateOfVisit;

    private int timeOfVisit;

    private Doctor doctor;

    private String note;
}
