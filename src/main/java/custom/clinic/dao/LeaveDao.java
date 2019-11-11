package custom.clinic.dao;

import custom.clinic.model.Leave;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LeaveDao extends JpaRepository<Leave, Integer> {
}
