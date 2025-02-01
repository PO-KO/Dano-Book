package com.dano.dano_book.constants;

import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import lombok.Getter;
import lombok.RequiredArgsConstructor;


@Getter
@RequiredArgsConstructor
public enum RoleEnum {
    USER(
            Set.of(
                    Permission.READ_AUTHOR,
                    Permission.READ_BOOK
            )
    ),
    ADMIN(
            Set.of(
                    Permission.READ_BOOK,
                    Permission.CREATE_BOOK,
                    Permission.UPDATE_BOOK,
                    Permission.DELETE_BOOK,
                    Permission.READ_AUTHOR,
                    Permission.CREATE_AUTHOR,
                    Permission.UPDATE_AUTHOR,
                    Permission.DELETE_AUTHOR
            )
    );

    private final Set<Permission> permissions;
    public Set<SimpleGrantedAuthority> getAuthorities() {
        var authorities = getPermissions()
                .stream()
                .map((permission) -> new SimpleGrantedAuthority(permission.name()))
                .collect(Collectors.toSet());

        authorities.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
        return authorities;
    }
}
