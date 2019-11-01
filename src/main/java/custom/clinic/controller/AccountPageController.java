package custom.clinic.controller;

import custom.clinic.model.Visit;
import custom.clinic.service.VisitService;
import org.springframework.stereotype.Controller;
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

    @GetMapping("/")
    public String accountPage() {
        return "/accountPage.html";
    }

    @PostMapping("/register-visit/")
    public String registerVisit(@Valid @ModelAttribute Visit visit) {
//        Visit visitToSave = Visit.builder().dateOfVisit(visit.getDateOfVisit()).timeOfVisit(visit.getTimeOfVisit()).build();
//        visitService.save(visitToSave);
        return "/account";
    }
}
