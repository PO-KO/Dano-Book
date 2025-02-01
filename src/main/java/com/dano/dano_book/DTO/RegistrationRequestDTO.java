package com.dano.dano_book.DTO;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record RegistrationRequestDTO(
        @NotBlank(message = "Firstname is required")
        String firstName,
        @NotBlank(message = "LastName is required")
        String lastName,
        @NotBlank(message = "Email is required")
        @Email(message = "Incorrect email pattern")
        String email,
        @Size(min = 8, message = "Password must be at least 8 chars")
        String password
) {
}
