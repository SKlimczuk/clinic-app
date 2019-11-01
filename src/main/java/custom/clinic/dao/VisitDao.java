package custom.clinic.dao;

import custom.clinic.model.User;
import custom.clinic.model.Visit;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VisitDao extends JpaRepository<Visit, Integer> {

    List<Visit> getAllByUser(User user);
}
