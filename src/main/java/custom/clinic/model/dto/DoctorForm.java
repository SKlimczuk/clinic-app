package custom.clinic.model.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@NoArgsConstructor
@Getter
@Setter
public class DoctorForm {

    private String name;

    private String surname;

    private String specialization;

    private String email;

    private String pesel;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateOfBirth;

    private String phone;

    private String password;

    private String matchingPassword;
}
