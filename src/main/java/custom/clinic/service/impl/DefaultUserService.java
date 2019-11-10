package custom.clinic.service.impl;

import custom.clinic.dao.RoleDao;
import custom.clinic.dao.UserDao;
import custom.clinic.model.User;
import custom.clinic.model.dto.RegisterForm;
import custom.clinic.service.UserService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Collections;

@Service
public class DefaultUserService implements UserService {

    @Resource
    private UserDao userDao;
    @Resource
    private BCryptPasswordEncoder passwordEncoder;
    @Resource
    private RoleDao roleDao;

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
                .email(userDto.getEmail())
                .pesel(userDto.getPesel())
                .phone(userDto.getPhone())
                .password(passwordEncoder.encode(userDto.getPassword()))
                .roles(Collections.singletonList(roleDao.findByName("ROLE_PATIENT")))
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

    @Override
    public boolean isDoctor(User user) {
        return user.getAuthorities().stream().anyMatch(r -> r.getAuthority().equals("ROLE_DOCTOR"));
    }

    @Override
    public boolean isPatient(User user) {
        return user.getAuthorities().stream().anyMatch(r -> r.getAuthority().equals("ROLE_PATIENT"));
    }
}
