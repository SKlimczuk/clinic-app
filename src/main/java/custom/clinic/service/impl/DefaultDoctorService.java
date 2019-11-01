package custom.clinic.service.impl;

import custom.clinic.dao.DoctorDao;
import custom.clinic.model.Doctor;
import custom.clinic.service.DoctorService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class DefaultDoctorService implements DoctorService {

    @Resource
    private DoctorDao doctorDao;

    @Override
    public List<Doctor> getAllDoctors() {
        return doctorDao.findAll();
    }
}
