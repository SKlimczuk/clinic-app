package custom.clinic.service.impl;

import custom.clinic.dao.DoctorDao;
import custom.clinic.model.Doctor;
import custom.clinic.model.User;
import custom.clinic.service.DoctorService;
import custom.clinic.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class DefaultDoctorService implements DoctorService {

    @Resource
    private DoctorDao doctorDao;
    @Resource
    private UserService userService;

    @Override
    public List<Doctor> getAllDoctors() {
        return doctorDao.findAll();
    }

    @Override
    public Doctor getDoctorByNameAndSurname(String name, String surname) {
        User potentialDoctor = userService.getUserByNameAndSurname(name, surname);
        Doctor doctor = new Doctor();

        try {
           doctor = doctorDao.getDoctorById(potentialDoctor.getId());
        } catch (NullPointerException e) {
            e.printStackTrace();
        }

        return doctor;
    }
}
