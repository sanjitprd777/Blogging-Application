package com.example.blogapp.payloads;

import com.example.blogapp.entities.Role;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@Getter
@Setter
public class UserDto {

    private int id;

    @NotEmpty
    @Size(min = 3, message = "Username must be min of 3 and max of 30 characters")
    private String name;

    @Email(message = "Email address is not valid!")
    private String email;

    @NotEmpty
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?!^[0-9])(?=.*[0-9]).{8,}$",
            message = "Password must contains lowercase, uppercase and number")
    @Size(min = 8, message = "Password must be at least 8 characters")
    private String password;

    @NotEmpty
    @Size(max = 100, message = "Content can not exceed 100 characters")
    private String about;

    private Set<RoleDto> roles = new HashSet<>();
}
