package custom.clinic.service.impl;

import custom.clinic.dao.NoteDao;
import custom.clinic.model.Note;
import custom.clinic.model.Visit;
import custom.clinic.model.dto.VisitForm;
import custom.clinic.service.NoteService;
import custom.clinic.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class DefaultNoteService implements NoteService {

    @Resource
    private NoteDao notesDao;

    @Override
    public void save(Note note) {
        try {
            notesDao.save(note);
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Note createNoteFromDto(VisitForm visitDto, Visit visit) {
        return Note.builder()
                .note(visitDto.getNote())
                .visit(visit)
                .build();
    }

    @Override
    public List<Note> getAllNotesForVisit(Visit visit) {
        return notesDao.findAllByVisit(visit);
    }
}
