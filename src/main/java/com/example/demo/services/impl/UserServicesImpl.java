package com.example.demo.services.impl;

import com.example.demo.exception.CustomException;
import com.example.demo.model.dto.UserDto;
import com.example.demo.model.entity.Animal;
import com.example.demo.model.entity.Clinic;
import com.example.demo.model.entity.Shelter;
import com.example.demo.model.entity.User;
import com.example.demo.model.enums.user.Role;
import com.example.demo.model.repository.ClinicRepository;
import com.example.demo.model.repository.ShelterRepository;
import com.example.demo.model.repository.UserRepository;
import com.example.demo.services.UserServices;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.security.Principal;
import java.time.LocalDateTime;
import java.util.*;

@Service
@RequiredArgsConstructor
public class UserServicesImpl implements UserServices {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final ShelterRepository shelterRepository;

    private final ClinicRepository clinicRepository;

    @PostConstruct
    public void runAfterObjectCreated() {
        User user = new User();
        user.setEmail("admin@admin");
        user.setName("admin");
        user.setPhone("0");
        user.setPassword(passwordEncoder.encode("0"));
        user.setId(1L);
        user.setActive(true);
        Set<Role> roles = user.getRoles();
        roles.add(Role.ROLE_ADMIN);
        user.setRoles(roles);
        userRepository.save(user);
    }

    @Override
    public void createUser(UserDto userDto) {

        User user = new User();

        userDto.setPatrol(userDto.getPatrol() == null ? false : true);

        if (userDto.getPatrol() != null && userDto.getEmail() != null && !userDto.getEmail().matches("^\\s*$")) {
            user.setPatrol(userDto.getPatrol());
        } else if (userDto.getPatrol()) {
            throw new CustomException("Для патрульного необходимо заполнить поле \"Эл. почта\"", HttpStatus.CONFLICT);
        }

        if (userDto.getName() != null
                && userDto.getPhone() != null
                && userDto.getSurname() != null
                && userDto.getPassword() != null
                && userDto.getCity() != null
                && !Objects.equals(userDto.getCity(), "")
                && !Objects.equals(userDto.getName(), "")
                && !Objects.equals(userDto.getPhone(), "")
                && !Objects.equals(userDto.getSurname(), "")
                && !Objects.equals(userDto.getPassword(), "")
        ) {
            userRepository.findByPhone(userDto.getPhone())
                    .ifPresent(h -> {
                        throw new CustomException("Клиент с таким номером уже зарегистрирован", HttpStatus.CONFLICT);
                    });
        } else {
            throw new CustomException("Заполните все поля!", HttpStatus.CONFLICT);
        }
        if (!userDto.getEmail().matches("^\\s*$")) {
            userRepository.findByEmail(userDto.getEmail())
                    .ifPresent(h -> { //если такой e-mail существует. то

                        throw new CustomException("Клиент с таким email уже зарегистрирован", HttpStatus.CONFLICT);

                    });
        }


        if (userDto.getPatrol() && userDto.getEmail() != null) {
            user.setPatrol(true);
        } else if (userDto.getPatrol()) {
            throw new CustomException("Для патрульного необходимо заполнить поле \"Эл. почта\"", HttpStatus.CONFLICT);
        }

        user.setPhone(userDto.getPhone());
        user.setName(userDto.getName());
        user.setSurname(userDto.getSurname());
        user.setEmail(userDto.getEmail());
        user.setCity(userDto.getCity());
        user.setAnimals(new ArrayList<>());
        user.setActive(true);

        Set<Role> roles = user.getRoles();
        roles.add(Role.ROLE_USER);
        user.setRoles(roles);


        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        userRepository.save(user);
    }

    @Override
    public String editProfile(Principal principal, UserDto userDto) {


        String phone = userRepository.findByPhone(getUserByPrincipal(principal).getPhone()).get().getPhone();
        String phone2 = userDto.getPhone();

        if (!Objects.equals(phone, phone2)) {
            userRepository.findByPhone(userDto.getPhone())
                    .ifPresent(h -> {
                        throw new CustomException("Данный номер используется другим клиентом", HttpStatus.CONFLICT);
                    });
        }


        if (userRepository.findALLByEmail(userDto.getEmail()).size() > 1) {
            if (!userDto.getEmail().matches("^\\s*$")) {
                throw new CustomException("Клиент с таким email уже существует", HttpStatus.CONFLICT);
            }
        }


        User user = getUserByPrincipal(principal);


        userDto.setPatrol(userDto.getPatrol() == null ? false : true);

        if (userDto.getPatrol() != null && userDto.getEmail() != null && !userDto.getEmail().matches("^\\s*$")) {
            user.setPatrol(userDto.getPatrol());
        } else if (userDto.getPatrol()) {
            throw new CustomException("Для патрульного необходимо заполнить поле \"Эл. почта\"", HttpStatus.CONFLICT);
        }

        user.setName(userDto.getName() == null || userDto.getName().matches("^\\s*$") ? user.getName() : userDto.getName());
        user.setSurname(userDto.getSurname() == null || userDto.getSurname().matches("^\\s*$") ? user.getSurname() : userDto.getSurname());
        user.setEmail(userDto.getEmail() == null || userDto.getEmail().matches("^\\s*$") ? user.getEmail() : userDto.getEmail());
        user.setCity(userDto.getCity() == null ? user.getCity() : userDto.getCity());
        user.setUpdatedAd(LocalDateTime.now());

        user.setPassword(userDto.getPassword() == null || userDto.getPassword().matches("^\\s*$") ? user.getPassword() : passwordEncoder.encode(userDto.getPassword()));


        userRepository.save(user);


        return "Данные клиента успешно обновлены";
    }

    @Override
    public User getUser(String phone) {
        return userRepository.findByPhone(phone).orElseThrow(() ->
                new CustomException("Такого клиента не существует, пожалуйста, зарегистрируйтесь!", HttpStatus.NOT_FOUND));
    }


    public List<User> userAll() {
        return userRepository.findAll();
    }

    @Override
    public List<Animal> animalAll(String phone) {
        return userRepository.findByPhone(phone).get().getAnimals();
    }

    @Override
    public User getUserByPrincipal(Principal principal) {
        if (principal == null) return new User();
        return userRepository.findByPhone(principal.getName()).get();
    }

    public void changeUserRoles(User user, Role role) {

        if (role != null) {
            if (role == Role.ROLE_EMPLOYEE) {
                Shelter shelter = shelterRepository.findByCity(user.getCity()).get();
                user.setShelter(shelter);
                List<User> users = shelter.getUsers();
                users.add(user);
                shelter.setUsers(users);
                if (user.getClinic() != null) {
                    Clinic clinic = user.getClinic();
                    List<User> users1 = clinic.getUsers();
                    users1.remove(user);
                    clinic.setUsers(users1);
                    user.setClinic(null);
                }
            } else if (role == Role.ROLE_VETERINARY) {
                Clinic clinic = clinicRepository.findByCity(user.getCity()).orElseThrow(() ->
                        new CustomException("Клиники в данном городе не существует", HttpStatus.NOT_FOUND));
                user.setClinic(clinic);
                List<User> users = clinic.getUsers();
                users.add(user);
                clinic.setUsers(users);
                if (user.getShelter() != null) {
                    Shelter shelter = user.getShelter();
                    List<User> users1 = shelter.getUsers();
                    users1.remove(user);
                    shelter.setUsers(users1);
                    user.setShelter(null);
                }

            } else if (user.getShelter() != null) {
                Shelter shelter = user.getShelter();
                List<User> users = shelter.getUsers();
                users.remove(user);
                shelter.setUsers(users);
                user.setShelter(null);
            } else if (user.getClinic() != null) {
                Clinic clinic = user.getClinic();
                List<User> users = clinic.getUsers();
                users.remove(user);
                clinic.setUsers(users);
                user.setClinic(null);
            }
            Set<Role> roles = new HashSet<>();
            roles.add(role);
            user.setRoles(roles);
            userRepository.save(user);
        }
    }

    public void editphone(Principal principal, String phone) {
        User user = userRepository.findByPhone(principal.getName()).get();
        user.setPhone(phone == null || phone.matches("^\\s*$") ? user.getPhone() : phone);
        userRepository.save(user);
    }

    @Override
    public List<User> findAllusers(Role roleUser) {
        return userRepository.findAllByRoles(roleUser);
    }

    @Override
    public User getUserId(Long id) {
        return userRepository.findById(id).get();
    }

}