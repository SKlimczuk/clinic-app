package custom.clinic.service;

import custom.clinic.model.Note;
import custom.clinic.model.Visit;
import custom.clinic.model.dto.VisitForm;

import java.util.List;

public interface NoteService {

    void save(Note note);

    Note createNoteFromDto(VisitForm visitDto, Visit visit);

    List<Note> getAllNotesForVisit(Visit visit);
}
