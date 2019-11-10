package custom.clinic.service;

import custom.clinic.model.Doctor;
import custom.clinic.model.Visit;
import custom.clinic.model.dto.VisitForm;

import java.time.LocalDate;
import java.util.List;

public interface VisitService {

    void save(Visit visit);

    Visit createVisitFromDto(VisitForm visitDto);

    Visit getVisitById(int id);

    List<Visit> getAllPreviousVisitsForUser(String username);

    List<Visit> getAllIncomingVisitsForUser(String username);

    List<Integer> getAvailableVisitsForChosenDoctorAndDay(Doctor doctor, LocalDate localDate);
}
