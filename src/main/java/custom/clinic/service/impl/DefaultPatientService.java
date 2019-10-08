package custom.clinic.service.impl;

import custom.clinic.dao.PatientDao;
import custom.clinic.model.Patient;
import custom.clinic.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DefaultPatientService implements PatientService {

    @Autowired
    PatientDao patientDao;


    @Override
    public void save(String name, String surname, String email, String pesel, String phone, String password) {
        Patient patient = Patient.builder().name(name).surname(surname).email(email).pesel(pesel).phone(phone).password(password).build();
        patientDao.save(patient);
    }
}
