package custom.clinic.controller;

import custom.clinic.model.User;
import custom.clinic.service.UserService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/admin")
@PreAuthorize("hasRole('ROLE_ADMIN')")
public class AdminPageController {

    @Resource
    private UserService userService;

    private final static String ROLE_PATIENT = "ROLE_PATIENT";
    private final static String ROLE_DOCTOR = "ROLE_DOCTOR";

    @GetMapping("/")
    public String adminPage(Model model) {
        model.addAttribute("patients", userService.getUsersByRole(ROLE_PATIENT));
        model.addAttribute("doctors", userService.getUsersByRole(ROLE_DOCTOR));

        return "adminPage.html";
    }

    @GetMapping("/remove")
    @ResponseBody
    public String getPatients(@RequestParam(name = "id") int id) {
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
}
