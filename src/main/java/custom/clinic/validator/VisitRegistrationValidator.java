package custom.clinic.validator;

import custom.clinic.model.Doctor;
import custom.clinic.model.dto.VisitForm;
import custom.clinic.service.DoctorService;
import custom.clinic.service.VisitService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static java.util.Objects.isNull;

@Component
public class VisitRegistrationValidator {

    private static final int REGISTER_MONTH_FROM_NOW = 2;

    @Resource
    private DoctorService doctorService;
    @Resource
    private VisitService visitService;

    public List<String> isVisitFormValid(VisitForm visitForm) {
        List<String> errors = new ArrayList<>();
        boolean isDateValid = true;

        if (!validDoctor(visitForm.getDoctor())) {
            errors.add("something is wrong with doctor");
        }

        if (!validDateIsBeforeNow(visitForm.getDateOfVisit())) {
            errors.add("date can not be from the past");
            isDateValid = false;
        }
        else if (!validDateIsAfterRegistrationFutureDate(visitForm.getDateOfVisit())) {
            errors.add("date cannot be after " + REGISTER_MONTH_FROM_NOW + "months (mentioned in info)");
            isDateValid = false;
        }

        if (!validTimeOfVisit(visitForm.getDoctor(), visitForm.getDateOfVisit(), visitForm.getTimeOfVisit()) && isDateValid) {
            errors.add("this hour is already taken, free visits: "
                    + visitService.getAvailableVisitsForChosenDoctorAndDay(visitForm.getDoctor(), visitForm.getDateOfVisit()));
        }

        return errors;
    }

    private boolean validDoctor(Doctor doctor) {
        return isNull(doctorService.getDoctorByNameAndSurname(doctor.getUser().getName(), doctor.getUser().getSurname()));
    }

    private boolean validDateIsBeforeNow(LocalDate date) {
        return !date.isBefore(LocalDate.now());
    }

    private boolean validDateIsAfterRegistrationFutureDate(LocalDate date) {
        return !date.isAfter(LocalDate.now().plusMonths(REGISTER_MONTH_FROM_NOW));
    }

    private boolean validTimeOfVisit(Doctor doctor, LocalDate date, int hour) {
        List<Integer> availableVisits = visitService.getAvailableVisitsForChosenDoctorAndDay(doctor, date);

        return availableVisits.stream().anyMatch(v -> v.equals(Integer.valueOf(hour)));
    }
}