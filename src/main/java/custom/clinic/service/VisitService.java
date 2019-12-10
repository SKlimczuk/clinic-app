package custom.clinic.service;

import custom.clinic.model.Doctor;
import custom.clinic.model.User;
import custom.clinic.model.Visit;
import custom.clinic.model.dto.VisitForm;

import java.time.LocalDate;
import java.util.List;

public interface VisitService {

    void save(Visit visit);

    Visit createVisitFromDto(VisitForm visitDto);

    Visit getVisitById(int id);

    List<Visit> getAllIncOrPrevVisitsForUser(User user, String when);

    List<Visit> getAllDailyDoctorsVisits(Doctor doctor);

    List<Integer> getAvailableVisitsForChosenDoctorAndDay(Doctor doctor, LocalDate localDate);

    void addNoteToVisit(int id, String note);

    LocalDate convertStringToLocalDate(String dateToConvert);
}
