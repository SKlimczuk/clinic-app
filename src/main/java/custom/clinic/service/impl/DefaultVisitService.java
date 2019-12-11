package custom.clinic.service.impl;

import custom.clinic.dao.VisitDao;
import custom.clinic.model.Doctor;
import custom.clinic.model.Note;
import custom.clinic.model.User;
import custom.clinic.model.Visit;
import custom.clinic.model.dto.VisitForm;
import custom.clinic.service.NoteService;
import custom.clinic.service.UserService;
import custom.clinic.service.VisitService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DefaultVisitService implements VisitService {

    @Resource
    private VisitDao visitDao;
    @Resource
    private NoteService noteService;
    @Resource
    private UserService userService;

    private final int FIRST_VISIT_TIME = 8;
    private final int LAST_VISIT_TIME = 15;

    @Override
    public void save(Visit visit) {
        if(visit != null) {
            visitDao.save(visit);
        }
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
    public Visit getVisitById(int id) {
        return visitDao.getById(id);
    }

    @Override
    public List<Visit> getAllIncOrPrevVisitsForUser(User user, String when) {

        List<Visit> visits = userService.isDoctor(user) ? visitDao.getAllByDoctor(user.getDoctor()) : visitDao.getAllByUser(user);

        if (visits.isEmpty()) {
            return Collections.emptyList();
        }

        return when.equals("PREV") ? getPrevVisits(visits) : getIncVisits(visits);
    }

    @Override
    public List<Visit> getAllDailyDoctorsVisits(Doctor doctor) {
        return visitDao.getAllByDoctorAndDateOfVisit(doctor, LocalDate.now()).stream()
                .sorted(Comparator.comparing(Visit::getTimeOfVisit))
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

    private List<Visit> getPrevVisits(List<Visit> visits) {
        return visits.stream()
                .filter(visit -> visit.getDateOfVisit().isBefore(LocalDate.now()))
                .sorted(Comparator.comparing(Visit::getDateOfVisit).reversed())
                .collect(Collectors.toList());
    }

    private List<Visit> getIncVisits(List<Visit> visits) {
        return visits.stream()
                .filter(visit -> visit.getDateOfVisit().isEqual(LocalDate.now()) || visit.getDateOfVisit().isAfter(LocalDate.now()))
                .sorted(Comparator.comparing(Visit::getDateOfVisit))
                .collect(Collectors.toList());
    }

    @Override
    public void addNoteToVisit(int id, String note) {
        Visit visit = visitDao.getById(id);
        User currentUser = userService.getCurrentUser();

        noteService.save(Note.builder().visit(visit).note(noteService.prepareNote(currentUser, note)).build());
    }

    @Override
    public LocalDate convertStringToLocalDate(String dateToConvert) {
        String[] params = dateToConvert.split("-");

        int year = Integer.parseInt(params[0]);
        int month = Integer.parseInt(params[1]);
        int day = Integer.parseInt(params[2]);

        return LocalDate.of(year, month, day);
    }
}
