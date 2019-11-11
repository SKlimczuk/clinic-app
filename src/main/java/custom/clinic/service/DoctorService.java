package custom.clinic.service;

import custom.clinic.model.Doctor;

import java.util.List;

public interface DoctorService {

    void save(Doctor doctor);

    List<Doctor> getAllDoctors();

    Doctor getDoctorByNameAndSurname(String name, String surname);
}
