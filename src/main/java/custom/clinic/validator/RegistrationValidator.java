package custom.clinic.validator;

import custom.clinic.model.dto.RegisterForm;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

@Component
public class RegistrationValidator {

    private static final String SPECIAL_CHAR_REGEX = "[^£§!@#$%^&*(),.?\":{}|<>~]+";
    private static final String EMAIL_REGEX = "^(.+)@(.+)\\.(.+)$";
    private static final String PHONE_REGEX = "^[0-9]{5,12}$";
    private static final String PESEL_REGEX = "^[0-9]{11}$";

    public List<String> isRegistrationFormValid(RegisterForm registerForm) {
        List<String> errors = new ArrayList<>();

        if(!isNameAndSurnameValid(registerForm.getName(), registerForm.getSurname())) {
            errors.add("invalid name/surname");
        }
        if(!isEmailValid(registerForm.getEmail())) {
            errors.add("invalid email");
        }
        if(!isPeselValid(registerForm.getPesel(), registerForm.getDateOfBirth())) {
            errors.add("invalid pesel/date of birth");
        }
        if(!isPhoneValid(registerForm.getPhone())) {
            errors.add("invalid phone");
        }
        if(isPasswordValid(registerForm.getPassword(), registerForm.getMatchingPassword())) {
            errors.add("password does not match");
        }

        return errors;
    }

    private boolean isNameAndSurnameValid(String name, String surname) {
        if("".equals(name) || "".equals(surname)){
            return false;
        }

        return Pattern.matches(SPECIAL_CHAR_REGEX, name) && Pattern.matches(SPECIAL_CHAR_REGEX, surname);
    }

    private boolean isEmailValid(String email) {
      if("".equals(email))
          return false;

      return Pattern.matches(EMAIL_REGEX, email);
    }

    private boolean isPeselValid(String pesel, LocalDate dateOfBirth) {
        if ("".equals(pesel) || dateOfBirth == null) {
            return false;
        }

        if(!Pattern.matches(PESEL_REGEX, pesel)) {
            return false;
        }

        if (dateOfBirth.isBefore(LocalDate.now().minusYears(110L))) {
            return false;
        }

        if(!comparePeselToDateOfBirth(pesel, dateOfBirth)) {
            return false;
        }

        return true;
    }

    private boolean comparePeselToDateOfBirth(String pesel, LocalDate date) {
        int yearPrefix = Math.floorDiv(date.getYear(), 100);
        int year = yearPrefix*100 + Integer.parseInt(pesel.substring(0, 2));
        int month = Integer.parseInt(pesel.substring(2, 4));
        int day = Integer.parseInt(pesel.substring(4, 6));
        //todo: extract it to method
        if (month < 1 || month > 12 || day < 1 || day > 31) {
            return false;
        }

        return date.compareTo(LocalDate.of(year,month,day)) == 0;
    }

    private boolean isPhoneValid(String phone) {
        if("".equals(phone)) {
            return false;
        }

        return Pattern.matches(PHONE_REGEX, phone);
    }

    private boolean isPasswordValid(String password, String matchingPassword) {
        if("".equals(password) || "".equals(matchingPassword)) {
            return false;
        }

        return !password.equals(matchingPassword);
    }
}
