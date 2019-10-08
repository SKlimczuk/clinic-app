package custom.clinic.service;

public interface PatientService {

    void save(String name, String surname, String email, String pesel, String phone, String password);
}
