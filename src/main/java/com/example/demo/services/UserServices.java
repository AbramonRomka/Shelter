package com.example.demo.services;

import com.example.demo.model.dto.UserDto;
import com.example.demo.model.entity.Animal;
import com.example.demo.model.entity.User;
import com.example.demo.model.enums.user.Role;

import java.security.Principal;
import java.util.List;

public interface UserServices {
    void createUser(UserDto userDto);

    String editProfile(Principal principal, UserDto userDto);

    User getUser(String phone);

    List<User> userAll();

    List<Animal> animalAll(String phone);

    User getUserByPrincipal(Principal principal);

    void changeUserRoles(User user, Role role);

    void editphone(Principal principal, String phone);

    List<User> findAllusers(Role roleUser);

    User getUserId(Long id);

}

