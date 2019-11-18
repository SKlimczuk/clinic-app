package custom.clinic.controller;

import custom.clinic.model.User;
import custom.clinic.model.dto.DoctorForm;
import custom.clinic.service.EmailService;
import custom.clinic.service.LeaveService;
import custom.clinic.service.UserService;
import custom.clinic.validator.RegistrationValidator;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/admin")
@PreAuthorize("hasRole('ROLE_ADMIN')")
public class AdminPageController {

    @Resource
    private UserService userService;
    @Resource
    private RegistrationValidator registrationValidator;
    @Resource
    private LeaveService leaveService;
    @Resource
    private EmailService emailService;

    private final static String ROLE_PATIENT = "ROLE_PATIENT";
    private final static String ROLE_DOCTOR = "ROLE_DOCTOR";

    @GetMapping("/")
    public String adminPage(Model model) {
        model.addAttribute("patients", userService.getUsersByRole(ROLE_PATIENT));
        model.addAttribute("doctors", userService.getUsersByRole(ROLE_DOCTOR));
        model.addAttribute("doctor", new DoctorForm());
        model.addAttribute("leaves", leaveService.getAllLeaves());

        return "adminPage.html";
    }

    @PostMapping("/add/doctor")
    public String addDoctor(@ModelAttribute("doctor") DoctorForm doctorForm, RedirectAttributes redirectAttributes) {
        List<String> errors = registrationValidator.isDoctorFormValid(doctorForm);

        if (!errors.isEmpty()) {
            redirectAttributes.addFlashAttribute("errors", errors);
            redirectAttributes.addFlashAttribute("isDoctorFormValid", false);
            return "redirect:/admin/";
        }

        redirectAttributes.addFlashAttribute("isDoctorFormValid", true);
        userService.save(userService.createDoctorFromDto(doctorForm));

        return "redirect:/admin/";
    }

    @GetMapping("/remove")
    @ResponseBody
    public String removeUser(@RequestParam(name = "id") int id) {
        userService.removeUser(id);

        return "success";
    }

    @GetMapping("/user")
    @ResponseBody
    public Map<String, Object> getUser(@RequestParam(name = "id") int id) {
        Map<String, Object> params = new HashMap<>();

        User user = userService.getUserById(id);
        params.put("user", user);

        return params;
    }

    @GetMapping("/doctor")
    @ResponseBody
    public Map<String, Object> getDoctor(@RequestParam(name = "id") int id) {
        Map<String, Object> params = new HashMap<>();

        User user = userService.getUserById(id);
        params.put("user", user);

        return params;
    }

    @GetMapping("/update/patient")
    @ResponseBody
    public String updatePatient(
            @RequestParam(name = "id") int id,
            @RequestParam(name = "name") String name,
            @RequestParam(name = "surname") String surname,
            @RequestParam(name = "email") String email,
            @RequestParam(name = "phone") String phone,
            @RequestParam(name = "pesel") String pesel,
            @RequestParam(name = "password") String password)
    {
        userService.updateUser(id, name, surname, email, phone, pesel, password);

        return "success";
    }

    @GetMapping("/update/doctor")
    @ResponseBody
    public String updateDoctor(
            @RequestParam(name = "id") int id,
            @RequestParam(name = "name") String name,
            @RequestParam(name = "surname") String surname,
            @RequestParam(name = "specialization") String specialization,
            @RequestParam(name = "email") String email,
            @RequestParam(name = "phone") String phone,
            @RequestParam(name = "pesel") String pesel,
            @RequestParam(name = "password") String password)
    {
        userService.updateDoctor(id, name, surname, specialization, email, phone, pesel, password);

        return "success";
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

    @GetMapping("/leave/status")
    @ResponseBody
    public String changeLeaveStatus(@RequestParam(name = "id") int id, @RequestParam(name = "status") boolean status) {
        leaveService.changeLeaveStatus(id, status);

        return "success";
    }
}
