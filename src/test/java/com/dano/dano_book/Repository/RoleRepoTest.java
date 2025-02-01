package com.dano.dano_book.Repository;

import com.dano.dano_book.entity.Role;
import com.dano.dano_book.repository.RoleRepo;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@RequiredArgsConstructor
class RoleRepoTest {

    @Autowired
    private RoleRepo repo;

    @Test
    void addRoles() {
        Role role = Role
                .builder()
                .name("ADMIN")
                .build();
        repo.save(role);
    }
}