package com.example.demo.model.repository;

import com.example.demo.model.entity.User;
import com.example.demo.model.enums.user.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByPhone(String phone);

    List<User> findALLByEmail(String email);

    Optional<User> findByEmail(String email);

    List<User> findAllByRoles(Role role);

}