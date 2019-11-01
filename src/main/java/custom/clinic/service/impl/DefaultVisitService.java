package custom.clinic.service.impl;

import custom.clinic.dao.UserDao;
import custom.clinic.dao.VisitDao;
import custom.clinic.model.User;
import custom.clinic.model.Visit;
import custom.clinic.service.VisitService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DefaultVisitService implements VisitService {

    @Resource
    private VisitDao visitDao;
    @Resource
    private UserDao userDao;

    @Override
    public void save(Visit visit) {
        visitDao.save(visit);
    }

    @Override
    public List<Visit> getAllPreviousVisitsForUser(String username) {
        User user = userDao.findUserByEmail(username);

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
        User user = userDao.findUserByEmail(username);

        List<Visit> visits = visitDao.getAllByUser(user);

        if (visits.isEmpty()) {
            return Collections.emptyList();
        }

        return visits.stream()
                .filter(visit -> visit.getDateOfVisit().isAfter(LocalDate.now()))
                .collect(Collectors.toList());
    }
}
