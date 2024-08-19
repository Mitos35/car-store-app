package my.code.dto;

import my.code.database.entity.Gender;
import my.code.database.entity.Role;

import java.util.Set;

public record UserDto(
        Long id,
        String firstName,
        String lastName,
        String email,
        String username,
        String password,
        Gender gender,
        double balance,
        int age,
        Set<Role> roles) {
}
