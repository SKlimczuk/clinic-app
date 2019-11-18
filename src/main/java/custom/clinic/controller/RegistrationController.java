package custom.clinic.controller;

import custom.clinic.model.dto.RegisterForm;
import custom.clinic.service.EmailService;
import custom.clinic.service.UserService;
import custom.clinic.validator.RegistrationValidator;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/register")
public class RegistrationController {

    @Resource
    private UserService userService;
    @Resource
    private EmailService emailService;
    @Resource
    private RegistrationValidator registrationValidator;

    @GetMapping("/")
    public String registerPage(Model model)
    {
        model.addAttribute("user", new RegisterForm());
        return "registerPage.html";
    }

    @PostMapping("/user")
    public String afterRegister(@Valid @ModelAttribute("user") RegisterForm registerForm, Model model)
    {
        List<String> errors = registrationValidator.isRegistrationFormValid(registerForm);

        if(!errors.isEmpty())
        {
            model.addAttribute("errors", errors);
            model.addAttribute("isFormValid", false);
            return "registerPage.html";
        }

        model.addAttribute("isFormValid", true);

        userService.save(userService.createUserFromDto(registerForm));

//        emailService.sendEmail("klimczukszymon@gmail.com", "hello", "test");

        return "redirect:/";
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
