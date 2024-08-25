package my.code.core.application.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.*;
import lombok.experimental.FieldDefaults;
import my.code.core.application.validator.UserAgeConstraint;
import my.code.core.database.entity.Gender;
import my.code.core.database.entity.Role;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
    public class UserRequestDto {

    @Schema(description = "User firs name", example = "Ivan")
    @NotBlank
    String firstName;

    @Schema(description = "User last name", example = "Ivanov")
    @NotBlank
    String lastName;

    @Schema(description = "User email", example = "Ivanov@mail.com")
    @NotNull
    @Email
    String email;

    @Schema(description = "User password", example = "Qwerty123456")
    @Pattern(regexp = "^(?=.*\\d)(?=.*[a-zA-Z])[0-9a-zA-Z]{6,}$", message = "Password must contain at least one digit, one letter, and be at least 6 characters long.")
    @NotNull(message = "Password is required")
    String password;

    @Schema(description = "User gender", example = "MALE")
    @NotNull
    Gender gender;

    BigDecimal balance;

    @Schema(description = "User age", example = "21")
    @UserAgeConstraint
    int age;

    @Schema(description = "User role", example = "USER")
    Role role;
}
