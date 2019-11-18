package custom.clinic.controller;

import custom.clinic.model.User;
import custom.clinic.model.Visit;
import custom.clinic.model.dto.LeaveForm;
import custom.clinic.model.dto.VisitForm;
import custom.clinic.service.*;
import custom.clinic.validator.LeaveValidator;
import custom.clinic.validator.VisitRegistrationValidator;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/account")
public class AccountPageController {

    private static final String PREVIOUS = "PREV";
    private static final String INCOMING = "INC";

    @Resource
    private VisitService visitService;
    @Resource
    private DoctorService doctorService;
    @Resource
    private VisitRegistrationValidator visitValidator;
    @Resource
    private NoteService noteService;
    @Resource
    private UserService userService;
    @Resource
    private LeaveValidator leaveValidator;
    @Resource
    private LeaveService leaveService;

    @GetMapping("/")
    public String accountPage(Model model) {
        User currentUser = userService.getCurrentUser();

        model.addAttribute("prevVisits", visitService.getAllIncOrPrevVisitsForUser(currentUser, PREVIOUS));
        model.addAttribute("incVisits", visitService.getAllIncOrPrevVisitsForUser(currentUser, INCOMING));

        if (userService.isDoctor(currentUser)) {
            model.addAttribute("dailyVisits", visitService.getAllDailyDoctorsVisits(currentUser.getDoctor()));
            model.addAttribute("leave", new LeaveForm());
        }
        else {
            model.addAttribute("visit", new VisitForm());
            model.addAttribute("doctors", doctorService.getAllDoctors());
        }

        return "/accountPage.html";
    }

    @PostMapping("/register/visit")
    public String registerVisit(@Valid @ModelAttribute("visit") VisitForm visitForm, final RedirectAttributes redirectAttributes) {
        List<String> errors = visitValidator.isVisitFormValid(visitForm);

        if(!errors.isEmpty()) {
            redirectAttributes.addFlashAttribute("errors", errors);
            redirectAttributes.addFlashAttribute("isFormValid", false);
            return "redirect:/account/";
        }
        redirectAttributes.addFlashAttribute("isFormValid", true);

        Visit visit = visitService.createVisitFromDto(visitForm);
        visitService.save(visit);

        if(!"".equals(visitForm.getNote())) {
            noteService.save(noteService.createNoteFromDto(visitForm, visit));
        }

        return "redirect:/account/";
    }

    @GetMapping(path = "/notes", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Map<String, Object> getNotesForVisit(@RequestParam int id) {
        Map<String, Object> result = new HashMap<>();

        result.put("notes", noteService.getAllNotesForVisit(visitService.getVisitById(id)));

        return result;
    }

    @GetMapping(path = "/add-note")
    @ResponseBody
    public String getNotesForVisit(@RequestParam(name = "id") int id, @RequestParam(name = "note") String note) {
        visitService.addNoteToVisit(id, note);

        return "success";
    }

    @GetMapping(path = "/free-hours", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Map<String, Object> getFreeVisitsHours(@RequestParam int date) {
        Map<String, Object> result = new HashMap<>();

        return result;
    }

    @PostMapping("/leave")
    public String applyForLeave(@ModelAttribute("leave") LeaveForm leaveForm, final RedirectAttributes redirectAttributes) {
        List<String> errors = leaveValidator.isVisitFormValid(leaveForm);

        if(!errors.isEmpty()) {
            redirectAttributes.addFlashAttribute("leaveErrors", errors);
            redirectAttributes.addFlashAttribute("isLeaveFormValid", false);
            return "redirect:/account/";
        }

        redirectAttributes.addFlashAttribute("isLeaveFormValid", true);

        leaveService.save(leaveService.createLeaveFromDto(leaveForm));

        return "redirect:/account/";
    }

    @InitBinder
    public void initBinder(WebDataBinder binder)
    {
        binder.registerCustomEditor(
                Date.class,
                new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"),
                        true,
                        10));
    }
}
