package custom.clinic.configuration;

import custom.clinic.dao.DoctorDao;
import custom.clinic.dao.NotesDao;
import custom.clinic.dao.UserDao;
import custom.clinic.dao.VisitDao;
import custom.clinic.model.Doctor;
import custom.clinic.model.Notes;
import custom.clinic.model.User;
import custom.clinic.model.Visit;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.time.LocalDate;

@Component
public class InitialTestData implements ApplicationRunner {

    @Resource
    private UserDao userDao;
    @Resource
    private VisitDao visitDao;
    @Resource
    private DoctorDao doctorDao;
    @Resource
    private NotesDao notesDao;
    @Resource
    BCryptPasswordEncoder passwordEncoder;

    public void run(ApplicationArguments args) {
        User user = User.builder().email("w@w.pl").pesel("4").password(passwordEncoder.encode("pass")).build();

        User doctorUser = User.builder().name("n").surname("s").email("d@d.pl").pesel("5").password(passwordEncoder.encode("pass")).build();
        User doctorUser2 = User.builder().name("n1").surname("s1").email("d2@d2.pl").pesel("6").password(passwordEncoder.encode("pass")).build();

        userDao.save(User.builder().email("k@k.pl").pesel("1").password(passwordEncoder.encode("pass")).build());
        userDao.save(User.builder().email("s@s.pl").pesel("2").password(passwordEncoder.encode("pass")).build());
        userDao.save(User.builder().email("e@e.pl").pesel("3").password(passwordEncoder.encode("pass")).build());
        userDao.save(user);
        userDao.save(doctorUser);
        userDao.save(doctorUser2);

        Doctor doctor = Doctor.builder().user(doctorUser).specialization("doc1").build();
        Doctor doctor2 = Doctor.builder().user(doctorUser2).specialization("doc1").build();

        doctorDao.save(doctor);
        doctorDao.save(doctor2);

        visitDao.save(Visit.builder().dateOfVisit(LocalDate.now()).timeOfVisit(9).user(user).doctor(doctor).build());
        visitDao.save(Visit.builder().dateOfVisit(LocalDate.of(2020,12,23)).timeOfVisit(9).user(user).doctor(doctor).build());
        visitDao.save(Visit.builder().dateOfVisit(LocalDate.of(2021,12,23)).timeOfVisit(9).user(user).doctor(doctor).build());
        visitDao.save(Visit.builder().dateOfVisit(LocalDate.of(2022,12,23)).timeOfVisit(9).user(user).doctor(doctor).build());
        visitDao.save(Visit.builder().dateOfVisit(LocalDate.of(2008,11,9)).timeOfVisit(9).user(user).doctor(doctor).build());
        visitDao.save(Visit.builder().dateOfVisit(LocalDate.of(2008,11,9)).timeOfVisit(9).user(user).doctor(doctor).build());
        visitDao.save(Visit.builder().dateOfVisit(LocalDate.of(2007,11,9)).timeOfVisit(9).user(user).doctor(doctor).build());
        visitDao.save(Visit.builder().dateOfVisit(LocalDate.of(2006,11,9)).timeOfVisit(9).user(user).doctor(doctor).build());

        notesDao.save(Notes.builder().note("note1").user(user).build());
        notesDao.save(Notes.builder().note("note2").user(user).build());
        notesDao.save(Notes.builder().note("note3").user(user).build());
    }
}