package custom.clinic.validator;

import custom.clinic.model.dto.LeaveForm;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
public class LeaveValidator {

    public List<String> isVisitFormValid(LeaveForm leaveForm) {
        List<String> errors = new ArrayList<>();

        if (isBeginIsAfterEnd(leaveForm.getStartLeave(), leaveForm.getEndLeave())) {
            errors.add("Begin of the leave can not be after end.");
        }
        if (isDateIsFromPast(leaveForm.getStartLeave(), leaveForm.getEndLeave())) {
            errors.add("Dates cannot be from past");
        }

        return errors;
    }

    private boolean isDateIsFromPast(LocalDate begin, LocalDate end) {
        return begin.isBefore(LocalDate.now()) || end.isBefore(LocalDate.now());
    }

    private boolean isBeginIsAfterEnd(LocalDate begin, LocalDate end) {
        return begin.isAfter(end);
    }
}
