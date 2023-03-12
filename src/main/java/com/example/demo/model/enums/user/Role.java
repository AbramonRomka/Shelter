package com.example.demo.model.enums.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;

@Getter
@AllArgsConstructor
public enum Role implements GrantedAuthority {
    ROLE_USER("Пользователь"),
    ROLE_EMPLOYEE("Сотрудник приюта"),
    ROLE_VETERINARY("Сотрудник ветеринарной клиники"),
    ROLE_ADMIN("Администратор");

    private final String description;

    @Override
    public String getAuthority() {
        return name();
    }
}
