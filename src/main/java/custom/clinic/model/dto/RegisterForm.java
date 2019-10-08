package custom.clinic.model.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.UniqueElements;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Email;
import java.util.Date;

@NoArgsConstructor
@Getter
@Setter
public class RegisterForm {

    private String name;

    private String surname;

    @Email
    private String email;

    private String pesel;

    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private Date dateOfBirth;

    private String phone;

    private String password;

    private String matchingPassword;

}
