package custom.clinic.service;

import custom.clinic.model.User;
import custom.clinic.model.dto.DoctorForm;
import custom.clinic.model.dto.RegisterForm;

import java.util.List;

public interface UserService {

    User getUserByNameAndSurname(String name, String Surname);

    User getUserByEmail(String email);

    void save(User user);

    User createUserFromDto(RegisterForm userDto);

    User createDoctorFromDto(DoctorForm doctorDto);

    User getCurrentUser();

    boolean isDoctor(User user);

    boolean isPatient(User user);

    List<User> getUsersByRole(String roleName);

    void updateUser(int id, String name, String surname, String email, String phone, String pesel, String password);

    void updateDoctor(int id, String name, String surname, String specialization, String email, String phone, String pesel, String password);

    void removeUser(int id);

    User getUserById(int id);

    User getUserByPesel(String pesel);
}
