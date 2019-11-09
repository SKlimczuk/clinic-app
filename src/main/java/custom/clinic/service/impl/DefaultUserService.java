package custom.clinic.service.impl;

import custom.clinic.dao.UserDao;
import custom.clinic.model.User;
import custom.clinic.model.dto.RegisterForm;
import custom.clinic.service.UserService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class DefaultUserService implements UserService {

    @Resource
    private UserDao userDao;
    @Resource
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public void save(User user) {
        try {
            userDao.save(user);
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

    @Override
    public User getCurrentUser() {
        return  (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    @Override
    public User getUserByNameAndSurname(String name, String surname) {
        return userDao.findUserByNameAndSurname(name, surname);
    }

    @Override
    public User getUserByEmail(String email) {
        return userDao.findUserByEmail(email);
    }
}
