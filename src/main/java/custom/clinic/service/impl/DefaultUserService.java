package custom.clinic.service.impl;

import custom.clinic.dao.UserDao;
import custom.clinic.model.User;
import custom.clinic.model.dto.RegisterForm;
import custom.clinic.service.RoleService;
import custom.clinic.service.UserService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.parameters.P;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DefaultUserService implements UserService {

    @Resource
    private UserDao userDao;
    @Resource
    private BCryptPasswordEncoder passwordEncoder;
    @Resource
    private RoleService roleService;

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
                .roles(Collections.singletonList(roleService.findByName("ROLE_PATIENT")))
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

    @Override
    public List<User> getUsersByRole(String roleName) {
        List<User> users = userDao.findAll().stream()
                .filter(u -> u.getRoles().contains(roleService.findByName(roleName)))
                .collect(Collectors.toList());

        users.removeIf(u -> u.getRoles().contains(roleService.findByName("ROLE_ADMIN")));

        return users;
    }

    @Override
    public void removeUser(int id) {
        Optional<User> opUser = userDao.findById(id);

        opUser.ifPresent(user -> userDao.delete(user));
    }

    @Override
    public User getUserById(int id) {
        return userDao.getOne(id);
    }

    @Override
    public void updateUser(int id, String name, String surname, String email, String phone, String pesel, String password) {
        User userToUpdate = userDao.getOne(id);

        if (!"".equals(name)) {
            userToUpdate.setName(name);
        }
        if (!"".equals(surname)) {
            userToUpdate.setSurname(surname);
        }
        if (!"".equals(phone)) {
            userToUpdate.setPhone(phone);
        }
        if (!"".equals(email)) {
            userToUpdate.setEmail(email);
        }
        if (!"".equals(pesel)) {
            userToUpdate.setPesel(pesel);
        }
        if (!"".equals(password)) {
            userToUpdate.setPassword(passwordEncoder.encode(password));
        }

        userDao.save(userToUpdate);
    }

    @Override
    public void updateDoctor(int id, String name, String surname, String specialization, String email, String phone, String pesel, String password) {
        User userToUpdate = userDao.getOne(id);

        if (!"".equals(name)) {
            userToUpdate.setName(name);
        }
        if (!"".equals(surname)) {
            userToUpdate.setSurname(surname);
        }
        if (!"".equals(surname)) {
            userToUpdate.getDoctor().setSpecialization(specialization);
        }
        if (!"".equals(phone)) {
            userToUpdate.setPhone(phone);
        }
        if (!"".equals(email)) {
            userToUpdate.setEmail(email);
        }
        if (!"".equals(pesel)) {
            userToUpdate.setPesel(pesel);
        }
        if (!"".equals(password)) {
            userToUpdate.setPassword(passwordEncoder.encode(password));
        }

        userDao.save(userToUpdate);
    }
}
