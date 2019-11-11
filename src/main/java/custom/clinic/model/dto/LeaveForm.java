package custom.clinic.model.dto;

import custom.clinic.model.Doctor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@NoArgsConstructor
@Getter
@Setter
public class LeaveForm {

    private Doctor doctor;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate startLeave;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate endLeave;
}
