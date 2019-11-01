package custom.clinic.service.impl;

import custom.clinic.dao.UserDao;
import custom.clinic.model.User;
import custom.clinic.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DefaultUserService implements UserService {

    @Autowired
    UserDao patientDao;


    @Override
    public void save(String name, String surname, String email, String pesel, String phone, String password) {
        User user = User.builder().name(name).surname(surname).email(email).pesel(pesel).phone(phone).password(password).build();
        patientDao.save(user);
    }
}
