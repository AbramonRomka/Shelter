package com.example.demo.controllers;

import com.example.demo.model.dto.UserDto;
import com.example.demo.model.entity.Shelter;
import com.example.demo.model.enums.shelter.StatusShelter;
import com.example.demo.services.AnimalServices;
import com.example.demo.services.ClinicServices;
import com.example.demo.services.ShelterServices;
import com.example.demo.services.UserServices;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping()
@RequiredArgsConstructor
@Tag(name = "Клиенты")
public class UserController {
    private final UserServices userServices;
    private final ShelterServices shelterServices;
    private final AnimalServices animalServices;

    private final ClinicServices clinicServices;

    @GetMapping("/editprofile")
    public String editprofile(Model model, Principal principal) {
        List<Shelter> cities = shelterServices.findAllStatusShelters(StatusShelter.OPEN);
        model.addAttribute("cities", cities);
        model.addAttribute("citieslenght", cities.size());
        model.addAttribute("user", userServices.getUser(principal.getName()));
        return "editprofile";
    }

    @PostMapping("/editprofileimpl")
    public String editprofileimpl(UserDto userDto,
                                  Principal principal) {
        userServices.editProfile(principal, userDto);
        return "redirect:/profile";
    }

    @GetMapping("/editphone")
    public String editphone(Model model, Principal principal) {
        model.addAttribute("user", userServices.getUser(principal.getName()));
        return "editphone";
    }

    @PostMapping("/editphoneimpl")
    public String editphoneimpl(Principal principal, @RequestParam("phone") String phone) {

        userServices.editphone(principal, phone);

        return "redirect:/login";
    }


    @GetMapping("/")
    public String home(Model model, Principal principal) {
        model.addAttribute("users", userServices.userAll()); //передаём список всех клиентов
        model.addAttribute("user", userServices.getUserByPrincipal(principal)); //если зашел авторизированный пользователь.
        model.addAttribute("cities", shelterServices.findAllStatusShelters(StatusShelter.OPEN));
        return "head";
    }

    @GetMapping("/profile")
    public String profile(Model model, Principal principal) {
        model.addAttribute("user", userServices.getUser(principal.getName()));
        model.addAttribute("shelteranimals", shelterServices.animalAll(principal.getName()));
        model.addAttribute("clinicanimals", clinicServices.animalAll(principal.getName()));
        model.addAttribute("animals", userServices.animalAll(principal.getName()));
        model.addAttribute("booking", animalServices.findBooking(principal));
        return "profile";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/user/info/{id}")
    public String userInfo(@PathVariable Long id, Model model) {
        model.addAttribute("user", userServices.getUserId(id));
        return "user-info";
    }


    @GetMapping("/registration")
    public String registration(Model model) {
        List<Shelter> cities = shelterServices.findAllStatusShelters(StatusShelter.OPEN);

        if (cities.size() == 0) {
            return "sorry";
        }

        model.addAttribute("cities", cities);
        model.addAttribute("citieslenght", cities.size());
        return "registration";
    }

    @PostMapping("/registration")
    @Operation(summary = "Создание нового клиента")
    public String createUser(UserDto userDto) {

        userServices.createUser(userDto);
        return "redirect:/login";
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @GetMapping("/hello")
    public String securityUrl() {
        return "hello";
    }

    @PreAuthorize("hasAnyAuthority('ROLE_EMPLOYEE','ROLE_VETERENARY')")
    @GetMapping("/hello1")
    public String securityUrl1() {
        return "hello1";
    }

    @PreAuthorize("hasAuthority('ROLE_USER')")
    @GetMapping("/hello2")
    public String securityUrl2() {
        return "hello2";
    }
}
