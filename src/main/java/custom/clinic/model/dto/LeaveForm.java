package custom.clinic.model.dto;

import custom.clinic.model.Doctor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@NoArgsConstructor
@Getter
@Setter
public class LeaveForm {

    private Doctor doctor;

    private LocalDate startLeave;

    private LocalDate endLeave;
}
