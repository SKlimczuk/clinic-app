package custom.clinic.dao;

import custom.clinic.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDao extends JpaRepository<User, Integer> {

    User findUserByEmail(String email);

    User findUserByNameAndSurname(String name, String surname);

    User findUserByPesel(String pesel);

}
