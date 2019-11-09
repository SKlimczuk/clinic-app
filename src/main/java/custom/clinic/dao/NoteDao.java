package custom.clinic.dao;

import custom.clinic.model.Note;
import custom.clinic.model.Visit;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NoteDao extends JpaRepository<Note, Integer> {

    List<Note> findAllByVisit(Visit visit);
}
