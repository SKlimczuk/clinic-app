package custom.clinic.controller;

import custom.clinic.model.User;
import custom.clinic.model.Visit;
import custom.clinic.model.dto.VisitForm;
import custom.clinic.service.DoctorService;
import custom.clinic.service.NoteService;
import custom.clinic.service.VisitService;
import custom.clinic.validator.VisitRegistrationValidator;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/account")
public class AccountPageController {

    @Resource
    private VisitService visitService;
    @Resource
    private DoctorService doctorService;
    @Resource
    private VisitRegistrationValidator visitValidator;
    @Resource
    private NoteService noteService;

    @GetMapping("/")
    public String accountPage(Model model) {
        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        model.addAttribute("visit", new VisitForm());
        model.addAttribute("prevVisits", visitService.getAllPreviousVisitsForUser(currentUser.getUsername()));
        model.addAttribute("incVisits", visitService.getAllIncomingVisitsForUser(currentUser.getUsername()));
        model.addAttribute("doctors", doctorService.getAllDoctors());

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

//    @GetMapping("/visits/{id}")
//    public String getNotesForVisit() {
//
//    }

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
