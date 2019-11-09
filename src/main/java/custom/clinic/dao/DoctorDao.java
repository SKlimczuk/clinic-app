package custom.clinic.dao;

import custom.clinic.model.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.print.Doc;
import java.util.List;

public interface DoctorDao extends JpaRepository<Doctor, Integer> {
    Doctor getDoctorById(int id);
}
