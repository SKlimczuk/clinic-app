package custom.clinic.service;

import custom.clinic.model.User;
import custom.clinic.model.dto.RegisterForm;

public interface UserService {

    User getUserByNameAndSurname(String name, String Surname);

    User getUserByEmail(String email);

    void save(User user);

    User createUserFromDto(RegisterForm userDto);

    User getCurrentUser();

    boolean isDoctor(User user);

    boolean isPatient(User user);
}
