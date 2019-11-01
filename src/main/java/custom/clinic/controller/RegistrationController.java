package custom.clinic.controller;

import custom.clinic.model.dto.RegisterForm;
import custom.clinic.service.UserService;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
@RequestMapping("/register")
public class RegistrationController {

    @Resource
    UserService userService;
    @Resource
    BCryptPasswordEncoder passwordEncoder;

    @GetMapping("/")
    public String registerPage(Model model)
    {
        model.addAttribute("user", new RegisterForm());
        return "registerPage.html";
    }

    @PostMapping("/user")
    public String afterRegister(@Valid @ModelAttribute("user") RegisterForm registerForm)
    {
        userService.save(
                registerForm.getName(),
                registerForm.getSurname(),
                registerForm.getEmail(),
                registerForm.getPesel(),
                registerForm.getPhone(),
                passwordEncoder.encode(registerForm.getPassword())
                );

        return "homePage.html";
    }

    @InitBinder
    public void initBinder(WebDataBinder binder){
        binder.registerCustomEditor(
                Date.class,
                new CustomDateEditor(new SimpleDateFormat("dd-MM-yyyy"),
                        true,
                        10));
    }
}
