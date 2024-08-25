package my.code.core.application.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;
import my.code.core.database.entity.Gender;
import my.code.core.database.entity.Role;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserResponseDto {
    Long id;
    String firstName;
    String lastName;
    String email;
    Gender gender;
    BigDecimal balance;
    int age;
    Role role;
}
