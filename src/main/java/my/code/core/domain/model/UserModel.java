package my.code.core.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import my.code.core.database.entity.Gender;
import my.code.core.database.entity.Role;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserModel {
    private Long id;
    private Gender gender;
    private String firstName;
    private String lastName;
    private String email;
    private Integer age;
    private BigDecimal balance;
    private Role role;
}
