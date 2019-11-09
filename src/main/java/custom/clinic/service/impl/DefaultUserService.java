package custom.clinic.service.impl;

import custom.clinic.dao.UserDao;
import custom.clinic.model.User;
import custom.clinic.model.dto.RegisterForm;
import custom.clinic.service.UserService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class DefaultUserService implements UserService {

    @Resource
    private UserDao patientDao;
    @Resource
    private BCryptPasswordEncoder passwordEncoder;


    @Override
    public void save(User user) {
        try{
            patientDao.save(user);
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    @Override
    public User createUserFromDto(RegisterForm userDto) {
        return User.builder()
                .name(userDto.getName())
                .surname(userDto.getSurname())
                .email(userDto.getPassword())
                .pesel(userDto.getPesel())
                .phone(userDto.getPhone())
                .password(passwordEncoder.encode(userDto.getPassword()))
                .build();
    }
}
