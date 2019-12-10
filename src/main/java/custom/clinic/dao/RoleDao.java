package custom.clinic.dao;

import custom.clinic.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleDao extends JpaRepository<Role, Integer> {

    Role findByName(String name);
}
