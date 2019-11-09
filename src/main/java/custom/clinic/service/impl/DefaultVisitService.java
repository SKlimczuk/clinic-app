package custom.clinic.service.impl;

import custom.clinic.dao.UserDao;
import custom.clinic.dao.VisitDao;
import custom.clinic.model.Doctor;
import custom.clinic.model.User;
import custom.clinic.model.Visit;
import custom.clinic.model.dto.VisitForm;
import custom.clinic.service.UserService;
import custom.clinic.service.VisitService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DefaultVisitService implements VisitService {

    @Resource
    private VisitDao visitDao;
    @Resource
    private UserService userService;

    private final int FIRST_VISIT_TIME = 8;
    private final int LAST_VISIT_TIME = 15;

    @Override
    public void save(Visit visit) {
        visitDao.save(visit);
    }

    @Override
    public Visit createVisitFromDto(VisitForm visitDto) {
        return Visit.builder()
                .user(userService.getCurrentUser())
                .doctor(visitDto.getDoctor())
                .dateOfVisit(visitDto.getDateOfVisit())
                .timeOfVisit(visitDto.getTimeOfVisit())
                .build();
    }

    @Override
    public List<Visit> getAllPreviousVisitsForUser(String username) {
        User user = userService.getUserByEmail(username);

        List<Visit> visits = visitDao.getAllByUser(user);

        if (visits.isEmpty()) {
            return Collections.emptyList();
        }

        return visits.stream()
                .filter(visit -> visit.getDateOfVisit().isBefore(LocalDate.now()))
                .collect(Collectors.toList());
    }

    @Override
    public List<Visit> getAllIncomingVisitsForUser(String username) {
        User user = userService.getUserByEmail(username);

        List<Visit> visits = visitDao.getAllByUser(user);

        if (visits.isEmpty()) {
            return Collections.emptyList();
        }

        return visits.stream()
                .filter(visit -> visit.getDateOfVisit().isAfter(LocalDate.now()))
                .collect(Collectors.toList());
    }

    @Override
    public List<Integer> getAvailableVisitsForChosenDoctorAndDay(Doctor doctor, LocalDate localDate) {
        List<Visit> reservedVisits = visitDao.getAllByDoctorAndDateOfVisit(doctor, localDate);

        List<Integer> scheduleList = prepareTimeScheduleForSingleDay();
        for (Visit reservedVisit : reservedVisits) {
            scheduleList.removeIf(s -> s.equals(Integer.valueOf(reservedVisit.getTimeOfVisit())));
        }

        return scheduleList;
    }

    private List<Integer> prepareTimeScheduleForSingleDay() {
        List<Integer> scheduleList = new ArrayList<>();

        for (int i = FIRST_VISIT_TIME; i <= LAST_VISIT_TIME; i++) {
            scheduleList.add(i);
        }

        return scheduleList;
    }
}
