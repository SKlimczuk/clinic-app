package custom.clinic.service;

import custom.clinic.model.Visit;

import java.util.List;

public interface VisitService {

    void save(Visit visit);

    List<Visit> getAllPreviousVisitsForUser(String username);

    List<Visit> getAllIncomingVisitsForUser(String username);
}
