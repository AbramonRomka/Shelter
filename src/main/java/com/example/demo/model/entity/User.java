package com.example.demo.model.entity;

import com.example.demo.model.enums.user.Role;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Getter
@Setter
@Entity
@Table(name = "users")

public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surname;

    @Column(name = "city")
    private String city;

    @Column(name = "e_mail")
    private String email;

    @Column(name = "patrol")
    private Boolean patrol = false;
    @Column(name = "phone", unique = true)
    private String phone;

    @Column(name = "password", length = 1000)
    private String password;

    @CreationTimestamp
    @Column(name = "created_ad", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP", updatable = false)
    private LocalDateTime createdAd;

    @Column(name = "updated_ad")
    private LocalDateTime updatedAd;

    @Column(name = "active")
    private boolean active;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "shelter_id")
    @JsonIgnore
    Shelter shelter;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "clinic_id")
    @JsonIgnore
    Clinic clinic;

    @Column(name = "animals_id")
    @OneToMany(cascade = CascadeType.ALL)
    private List<Animal> animals;

    @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "user_role",
            joinColumns = @JoinColumn(name = "user_id"))
    @Enumerated(EnumType.STRING)
    private Set<Role> roles = new HashSet<>();

    public boolean isEmployee() {

        return roles.contains(Role.ROLE_EMPLOYEE) || roles.contains(Role.ROLE_VETERINARY);
    }

    public boolean isAdmin() {
        return roles.contains(Role.ROLE_ADMIN);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
    }

    @Override
    public String getUsername() {
        return phone;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return active;
    }
}
