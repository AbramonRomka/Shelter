package com.example.demo.controllers;

import com.example.demo.model.dto.ClinicDto;
import com.example.demo.model.dto.ShelterDto;
import com.example.demo.model.entity.Animal;
import com.example.demo.model.entity.Clinic;
import com.example.demo.model.entity.Shelter;
import com.example.demo.model.entity.User;
import com.example.demo.model.enums.user.Role;
import com.example.demo.services.AnimalServices;
import com.example.demo.services.ClinicServices;
import com.example.demo.services.ShelterServices;
import com.example.demo.services.UserServices;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping()
@RequiredArgsConstructor
@Tag(name = "Админка")
@PreAuthorize("hasAuthority('ROLE_ADMIN')")
public class AdminController {
    private final UserServices userServices;
    private final ShelterServices shelterServices;
    private final ClinicServices clinicServices;

    private final AnimalServices animalServices;

    @GetMapping("/admin")
    public String admin(Model model) {

        List<User> users = userServices.findAllusers(Role.ROLE_USER);
        users.addAll(userServices.findAllusers(null));

        List<User> employees = userServices.findAllusers(Role.ROLE_EMPLOYEE);
        employees.addAll(userServices.findAllusers(Role.ROLE_VETERINARY));
        employees.addAll(userServices.findAllusers(Role.ROLE_ADMIN));

        List<Shelter> shelters = shelterServices.findAllshelters();

        List<Clinic> clinics = clinicServices.findAllclinic();

        List<Animal> animals = animalServices.findAllAnimals();

        model.addAttribute("users", users);
        model.addAttribute("employees", employees);
        model.addAttribute("animals", animals);
        model.addAttribute("shelters", shelters);
        model.addAttribute("citieslenght", shelters.size());
        model.addAttribute("clinics", clinics);
        return "admin";
    }

    @GetMapping("/admin/user/edit/{user}")
    public String userEdit(@PathVariable("user") User user, Model model) {
        model.addAttribute("user", user);
        model.addAttribute("roles", Role.values());
        return "user-edit";
    }

    @PostMapping("/admin/user/edit")
    public String userEdit(@RequestParam("userId") User user, @RequestParam("role") Role role) {
        userServices.changeUserRoles(user, role);
        return "redirect:/admin";
    }


    @PostMapping("/admin/createshelter")
    public String createshelter(ShelterDto shelterDto) {
        shelterServices.createShelter(shelterDto);
        return "redirect:/admin";
    }


    @PostMapping("/admin/createclinic")
    public String createclinic(ClinicDto clinicDto) {
        clinicServices.createClinic(clinicDto);
        return "redirect:/admin";
    }

}
