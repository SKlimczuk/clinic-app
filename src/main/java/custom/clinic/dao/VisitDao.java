package custom.clinic.dao;

import custom.clinic.model.Doctor;
import custom.clinic.model.User;
import custom.clinic.model.Visit;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface VisitDao extends JpaRepository<Visit, Integer> {

    Visit getById(int id);

    List<Visit> getAllByUser(User user);

    List<Visit> getAllByDoctor(Doctor doctor);

    List<Visit> getAllByDoctorAndDateOfVisit(Doctor doctor, LocalDate dateOfVisit);
}
