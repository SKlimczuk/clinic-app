package custom.clinic.configuration;

import custom.clinic.dao.*;
import custom.clinic.model.*;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.util.Collections;

@Component
public class InitialTestData implements ApplicationRunner {

    @Resource
    private UserDao userDao;
    @Resource
    private VisitDao visitDao;
    @Resource
    private DoctorDao doctorDao;
    @Resource
    private NoteDao notesDao;
    @Resource
    private BCryptPasswordEncoder passwordEncoder;
    @Resource
    private RoleDao roleDao;

    public void run(ApplicationArguments args) {
        Role patientRole = Role.builder().name("ROLE_PATIENT").build();
        Role adminRole = Role.builder().name("ROLE_ADMIN").build();
        Role doctorRole = Role.builder().name("ROLE_DOCTOR").build();

        roleDao.save(patientRole);
        roleDao.save(adminRole);
        roleDao.save(doctorRole);

        User user = User.builder().email("w@w.pl").pesel("4").password(passwordEncoder.encode("pass")).roles(Collections.singletonList(patientRole)).build();

        User doctorUser = User.builder().name("n").surname("s").email("d@d.pl").pesel("5").password(passwordEncoder.encode("pass")).roles(Collections.singletonList(doctorRole)).build();
        User doctorUser2 = User.builder().name("n1").surname("s1").email("d2@d2.pl").pesel("6").password(passwordEncoder.encode("pass")).roles(Collections.singletonList(doctorRole)).build();

        userDao.save(User.builder().email("k@k.pl").pesel("1").password(passwordEncoder.encode("pass")).roles(Collections.singletonList(patientRole)).build());
        userDao.save(User.builder().email("s@s.pl").pesel("2").password(passwordEncoder.encode("pass")).roles(Collections.singletonList(patientRole)).build());
        userDao.save(User.builder().email("e@e.pl").pesel("3").password(passwordEncoder.encode("pass")).roles(Collections.singletonList(patientRole)).build());
        userDao.save(user);
        userDao.save(doctorUser);
        userDao.save(doctorUser2);

        Doctor doctor = Doctor.builder().user(doctorUser).specialization("doc1").build();
        Doctor doctor2 = Doctor.builder().user(doctorUser2).specialization("doc1").build();

        doctorDao.save(doctor);
        doctorDao.save(doctor2);

        Visit visit = Visit.builder().user(user).dateOfVisit(LocalDate.now()).timeOfVisit(12).build();

        visitDao.save(visit);
        visitDao.save(Visit.builder().dateOfVisit(LocalDate.now()).timeOfVisit(9).user(user).doctor(doctor).build());
        visitDao.save(Visit.builder().dateOfVisit(LocalDate.of(2020,12,23)).timeOfVisit(9).user(user).doctor(doctor).build());
        visitDao.save(Visit.builder().dateOfVisit(LocalDate.of(2021,12,23)).timeOfVisit(9).user(user).doctor(doctor).build());
        visitDao.save(Visit.builder().dateOfVisit(LocalDate.of(2022,12,23)).timeOfVisit(9).user(user).doctor(doctor).build());
        visitDao.save(Visit.builder().dateOfVisit(LocalDate.of(2008,11,9)).timeOfVisit(9).user(user).doctor(doctor).build());
        visitDao.save(Visit.builder().dateOfVisit(LocalDate.of(2008,11,9)).timeOfVisit(9).user(user).doctor(doctor).build());
        visitDao.save(Visit.builder().dateOfVisit(LocalDate.of(2007,11,9)).timeOfVisit(9).user(user).doctor(doctor).build());
        visitDao.save(Visit.builder().dateOfVisit(LocalDate.of(2006,11,9)).timeOfVisit(9).user(user).doctor(doctor).build());

        notesDao.save(Note.builder().note("note1").visit(visit).build());
        notesDao.save(Note.builder().note("note2").visit(visit).build());
        notesDao.save(Note.builder().note("note3").visit(visit).build());
    }
}