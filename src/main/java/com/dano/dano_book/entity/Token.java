package com.dano.dano_book.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(
        name = "token_table"
)
public class Token {

    @Id
    @GeneratedValue
    private Integer tokenId;
    private String token;
    private LocalDate createdAt;
    private LocalDate expiresAt;
    private LocalDate validatedAt;

    @ManyToOne //  Many tokens to one user
    @JoinColumn(name = "userId", nullable = false)
    private User user;
}
