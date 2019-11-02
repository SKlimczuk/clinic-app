package custom.clinic.service;

import custom.clinic.model.Doctor;
import custom.clinic.model.Visit;

import java.time.LocalDate;
import java.util.List;

public interface VisitService {

    void save(Visit visit);

    List<Visit> getAllPreviousVisitsForUser(String username);

    List<Visit> getAllIncomingVisitsForUser(String username);

    List<Integer> getAvailableVisitsForChosenDoctorAndDay(Doctor doctor, LocalDate localDate);
}
