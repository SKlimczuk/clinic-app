package custom.clinic.configuration;

import custom.clinic.dao.*;
import custom.clinic.model.*;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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

        // ----- role of users
        Role patientRole = Role.builder().name("ROLE_PATIENT").build();
        Role adminRole = Role.builder().name("ROLE_ADMIN").build();
        Role doctorRole = Role.builder().name("ROLE_DOCTOR").build();

        roleDao.save(patientRole);
        roleDao.save(adminRole);
        roleDao.save(doctorRole);

        List<Role> roles = new ArrayList<>();
        roles.add(adminRole);
        roles.add(doctorRole);

        // ----- users : patients and doctors

        User user1 = User.builder().name("jan").surname("kowalski").email("u@u.pl").pesel("4").phone("123123123").password(passwordEncoder.encode("pass")).roles(Collections.singletonList(patientRole)).build();
        User user2 = User.builder().name("marek").surname("nowak").email("k@k.pl").pesel("1").phone("123123123").password(passwordEncoder.encode("pass")).roles(Collections.singletonList(patientRole)).build();
        User user3 = User.builder().name("kamil").surname("wozniak").email("s@s.pl").pesel("2").phone("123123123").password(passwordEncoder.encode("pass")).roles(Collections.singletonList(patientRole)).build();
        User user4 = User.builder().name("jurek").surname("dubiel").email("e@e.pl").pesel("3").phone("123123123").password(passwordEncoder.encode("pass")).roles(Collections.singletonList(patientRole)).build();

        User userDoctor1 = User.builder().name("marcin").surname("kowal").email("m@k.pl").pesel("5").phone("123123123").password(passwordEncoder.encode("pass")).roles(Collections.singletonList(doctorRole)).build();
        User userDoctor2 = User.builder().name("aleksander").surname("morski").email("a@dm.pl").pesel("6").phone("123123123").password(passwordEncoder.encode("pass")).roles(Collections.singletonList(doctorRole)).build();
        User userDoctor3 = User.builder().name("karol").surname("kawalek").email("admin@a.pl").pesel("62").phone("123123123").password(passwordEncoder.encode("pass")).roles(roles).build();

        userDao.save(user1);
        userDao.save(user2);
        userDao.save(user3);
        userDao.save(user4);
        userDao.save(userDoctor1);
        userDao.save(userDoctor2);
        userDao.save(userDoctor3);

        Doctor doctor1 = Doctor.builder().user(userDoctor1).specialization("dentist").build();
        Doctor doctor2 = Doctor.builder().user(userDoctor2).specialization("medic").build();
        Doctor doctor3 = Doctor.builder().user(userDoctor3).specialization("cardiolog").build();

        doctorDao.save(doctor1);
        doctorDao.save(doctor2);
        doctorDao.save(doctor3);

        //visits

        Visit todayVisit = Visit.builder().user(user1).dateOfVisit(LocalDate.now()).timeOfVisit(12).build();
        visitDao.save(todayVisit);

        visitDao.save(Visit.builder().dateOfVisit(LocalDate.now()).timeOfVisit(9).user(user1).doctor(doctor1).build());
        visitDao.save(Visit.builder().dateOfVisit(LocalDate.of(2020,12,23)).timeOfVisit(9).user(user1).doctor(doctor1).build());
        visitDao.save(Visit.builder().dateOfVisit(LocalDate.of(2021,12,23)).timeOfVisit(9).user(user1).doctor(doctor1).build());
        visitDao.save(Visit.builder().dateOfVisit(LocalDate.of(2022,12,23)).timeOfVisit(9).user(user1).doctor(doctor1).build());
        visitDao.save(Visit.builder().dateOfVisit(LocalDate.of(2008,11,9)).timeOfVisit(9).user(user1).doctor(doctor1).build());
        visitDao.save(Visit.builder().dateOfVisit(LocalDate.of(2008,11,9)).timeOfVisit(9).user(user1).doctor(doctor1).build());
        visitDao.save(Visit.builder().dateOfVisit(LocalDate.of(2007,11,9)).timeOfVisit(9).user(user1).doctor(doctor1).build());
        visitDao.save(Visit.builder().dateOfVisit(LocalDate.of(2006,11,9)).timeOfVisit(9).user(user1).doctor(doctor1).build());

        notesDao.save(Note.builder().note("note1").visit(todayVisit).build());
        notesDao.save(Note.builder().note("note2").visit(todayVisit).build());
        notesDao.save(Note.builder().note("note3").visit(todayVisit).build());
    }
}