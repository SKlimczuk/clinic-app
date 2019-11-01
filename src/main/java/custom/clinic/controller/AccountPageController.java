package custom.clinic.controller;

import custom.clinic.model.User;
import custom.clinic.model.dto.VisitForm;
import custom.clinic.service.DoctorService;
import custom.clinic.service.VisitService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.validation.Valid;

@Controller
@RequestMapping("/account")
public class AccountPageController {

    @Resource
    private VisitService visitService;
    @Resource
    private DoctorService doctorService;

    @GetMapping("/")
    public String accountPage(Model model) {
        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        model.addAttribute("visit", new VisitForm());
        model.addAttribute("prevVisits", visitService.getAllPreviousVisitsForUser(currentUser.getUsername()));
        model.addAttribute("incVisits", visitService.getAllIncomingVisitsForUser(currentUser.getUsername()));
        model.addAttribute("doctors", doctorService.getAllDoctors());

        return "/accountPage.html";
    }

    @PostMapping("/register-visit/")
    public String registerVisit(@Valid @ModelAttribute("visit") VisitForm visitForm) {


        return "/account";
    }
}
