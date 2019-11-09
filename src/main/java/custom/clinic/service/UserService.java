package custom.clinic.service;

import custom.clinic.model.User;
import custom.clinic.model.dto.RegisterForm;

public interface UserService {

    void save(User user);

    User createUserFromDto(RegisterForm userDto);
}
