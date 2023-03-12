package com.example.demo.controllers;

import com.example.demo.model.dto.AnimalDto;
import com.example.demo.model.dto.UserDto;
import com.example.demo.model.entity.Animal;
import com.example.demo.model.entity.Shelter;
import com.example.demo.model.entity.User;
import com.example.demo.model.enums.animals.AnimalsStatus;
import com.example.demo.services.AnimalServices;
import com.example.demo.services.ShelterServices;
import com.example.demo.services.UserServices;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping()
@RequiredArgsConstructor
@Tag(name = "Животные")
public class AnimalsController {
    private final UserServices userServices;
    private final ShelterServices shelterServices;
    private final AnimalServices animalServices;

    @GetMapping("/booking")
    public String booking(Model model, Principal principal) {
        Shelter shelter = shelterServices.getShelter(userServices
                .getUser(principal.getName()).getCity());
        model.addAttribute("animals", shelterServices.animalAllStatus(shelter, AnimalsStatus.IN_SHELTER));
        return "booking";
    }

    @GetMapping("/bookingimpl/{animal}")
    public String bookingimpl(@PathVariable("animal") Animal animal,
                              Principal principal) {
        animalServices.booking(animal, principal);
        return "redirect:/profile";
    }


    @GetMapping("/inshelter")
    public String inshelter(Principal principal, Model model) {
        model.addAttribute("animalStatus", AnimalsStatus.values());
        return "inshelter";
    }

    @PostMapping("/inshelterimpl")
    public String inshelterimpl(AnimalDto animalDto, Principal principal) {
        Animal animal = animalServices.getAnimal(animalServices.createAnimal(animalDto, principal.getName()));
        Long id = animal.getId();
        return "redirect:/animal/info/" + id;
    }

    @GetMapping("/outshelter/{animal}")
    public String outshelter(@PathVariable("animal") Animal animal,
                             Model model) {
        model.addAttribute("animal", animal);
        return "outshelter";
    }

    @PostMapping("/outshelterimpl")
    public String outshelterimpl(@RequestParam("animalId") Animal animal,
                                 @RequestParam("phone") String phone,
                                 Model model) {
        model.addAttribute("animal", animal);
        model.addAttribute("user", userServices.getUser(phone));
        return "accept-transfer";
    }

    @PostMapping("/transfer")
    public String transfer(@RequestParam("animalId") Animal animal,
                           @RequestParam("userId") User user,
                           Model model) {
        animalServices.transferAnimal(animal, user);
        model.addAttribute("animal", animal);
        return "redirect:/profile";
    }

    @GetMapping("/animal/info/{id}")
    public String userInfo(@PathVariable Long id, Model model) {
        model.addAttribute("animal", animalServices.getAnimal(id));
        return "animal-info";
    }


    @GetMapping("/statement")
    @Operation(summary = "Создать заявление от клиента")
    public String statement(Principal principal, Model model) {
        List<Shelter> cities = shelterServices.findAllshelters();
        model.addAttribute("cities", cities);
        model.addAttribute("citieslenght", cities.size());
        model.addAttribute("user", userServices.getUser(principal.getName()));
        return "statementuser";
    }

    @PostMapping("/statementCreateEmployee")
    @Operation(summary = "Создать заявление от сотрудника")
    public String createStatementEmployee(AnimalDto animalDto,
                                          UserDto userDto,
                                          Principal principal,
                                          @RequestParam("flag") boolean flag) {

        if (!flag) {
            animalServices.statementCreateUser(principal, animalDto);
        } else {
            animalServices.statementCreateEmployee(principal, animalDto, userDto);
        }
        return "redirect:/profile";
    }

    @GetMapping("/animal/edit/{animal}")
    @Operation(summary = "Изменение статуса животных")
    public String updateAnimal(@PathVariable("animal") Animal animal, Model model, Principal principal) {
        User user = userServices.getUser(principal.getName());
        model.addAttribute("user", user);
        model.addAttribute("animal", animal);
        model.addAttribute("roles", AnimalsStatus.values());
        return "animal-edit";
    }

    @PostMapping("/animal/edit")
    public String employeeEdit(@RequestParam("animalId") Animal animal,
                               @RequestParam AnimalsStatus animalsStatus,
                               @RequestParam String description) {
        animalServices.editAnimal(animal, animalsStatus, description);
        return "redirect:/profile";
    }
}



















