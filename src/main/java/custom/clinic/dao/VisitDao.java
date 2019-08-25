package custom.clinic.dao;

import custom.clinic.model.Visit;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VisitDao extends JpaRepository<Visit, Integer> {

    int getVisitsById(int id);

}
