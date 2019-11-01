package custom.clinic.dao;

import custom.clinic.model.Notes;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotesDao extends JpaRepository<Notes, Integer> {
}
